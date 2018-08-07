package com.sherlock.views.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sherlock.R;
import com.sherlock.adapters.ContactsAdapter;
import com.sherlock.models.UserContact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactSyncActivity extends AppCompatActivity {

    private static boolean mPermission;
    private Cursor cursor;
    private ArrayList<UserContact> mContactList = new ArrayList<>();
    private ArrayList<UserContact> mUniqueContactList = new ArrayList<>();
    public static final int READ_CONTACTS_PERMISSIONS_REQUEST = 100;

    @BindView(R.id.rv_contacts)
    RecyclerView contactsRecyclerView;
    @BindView(R.id.tv_empty)
    TextView emptyTextView;
    private ContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sync);
        ButterKnife.bind(this);

        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(ContactSyncActivity.this));

        fetchContacts();
    }


    public void fetchContacts() {
        checkingDeviceOSAndPermissions();
    }


    /**
     * Checking the Device OS version here.
     */
    public void checkingDeviceOSAndPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            mPermission = checkReadContactsOfUserPermission(ContactSyncActivity.this);

            //Permission is granted - value true here.
            if (mPermission) {
                getContacts();
            }
        } else {
            getContacts();
        }
    }


    /**
     * Initiating the contact fetching with showing progress dialog here .
     */
    public void getContacts() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                getContactList();
            }
        }).start();
    }


    /**
     * Fetching Contacts from User Device after permission granted here.
     */
    private void getContactList() {
        String name, phonenumber;
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone
                .CONTENT_URI, null, null, null, null);
        UserContact userContact;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                userContact = new UserContact(name, phonenumber);
                mContactList.add(userContact);

            }
            cursor.close();
        }

        Log.d("getContactList", "data=" + mContactList.size());
        mUniqueContactList = removeDuplicates(mContactList);

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                setContactRecyclerView(mUniqueContactList);
            }
        });
    }

    private void setContactRecyclerView(ArrayList<UserContact> contactsList) {
        if (mContactList.size() > 0) {
            emptyTextView.setVisibility(View.GONE);
            mContactsAdapter = new ContactsAdapter(ContactSyncActivity.this, contactsList);
            contactsRecyclerView.setAdapter(mContactsAdapter);
        } else {
            contactsRecyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        }
    }


    /**
     * Checking Read Contact Permission handle here.
     *
     * @param context
     */
    private Boolean checkReadContactsOfUserPermission(Context context) {
        if (ContextCompat.checkSelfPermission(ContactSyncActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
            return false;
        } else {
            return true;
        }

    }

    /**
     * Request of Permissions Result comes here.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_CONTACTS_PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkingDeviceOSAndPermissions();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(ContactSyncActivity.this, Manifest.permission.READ_CONTACTS)) {
                        checkingDeviceOSAndPermissions();
                    }
                }
        }
    }

    /*Comparator for sorting the list of UserContacts in ContactsList*/
    public Comparator<UserContact> contactSortComparator = new Comparator<UserContact>() {

        public int compare(UserContact x, UserContact y) {
            String user1 = x.getUsername().toUpperCase();
            String user2 = y.getUsername().toUpperCase();

            //ascending order
            return user1.compareTo(user2);
        }
    };


    /**
     * This method will remove the duplicates from given Arraylist and provides a new Arraylist with unique objects.
     */
    private static ArrayList<UserContact> removeDuplicates(ArrayList<UserContact> list) {
        Set set = new TreeSet(new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 != null && o2 != null && ((UserContact) o1).userphone != null && (
                        (UserContact) o2).userphone != null) {
                    if (((UserContact) o1).getUserphone().equalsIgnoreCase(((UserContact) o2).getUserphone())) {
                        return 0;
                    }
                    return 1;
                }
                return 0;
            }
        });
        set.addAll(list);

        final ArrayList newListWithUniqueObjects = new ArrayList(set);
        return newListWithUniqueObjects;
    }

    /**
     * Handling Back Press Event
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
