package com.sherlock.views.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sherlock.R;
import com.sherlock.adapters.SmsAdapter;
import com.sherlock.models.SmsItem;
import com.sherlock.utils.CommonItemSpaceDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SentFragment extends Fragment {


    public SentFragment() {
        // Required empty public constructor
    }

    private Boolean mPermission = false;
    public static final int READ_SMS_PERMISSIONS_REQUEST = 100;

    private SmsAdapter mSmsAdapter;

    @BindView(R.id.sent_sms_rv)
    RecyclerView mSmsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sent, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkingDeviceOSAndPermissions();
    }

    private void checkingDeviceOSAndPermissions() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            mPermission = checkReadSmsPermission();

            //Permission is granted - value true here.
            if (mPermission) {

                // list of SMS items
                List<SmsItem> mSmsItemList = new ArrayList();
                //a SMS Object
                SmsItem mSmsItem;

                Uri uriSms = Uri.parse("content://sms/sent");
                final Cursor cursor = getActivity().getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"}, null, null, null);

                while (cursor.moveToNext()) {
                    String smsAddress = cursor.getString(1);
                    String smsContent = cursor.getString(3);

                    mSmsItem = new SmsItem();

                    //setting the sms address or number
                    mSmsItem.setNumber(smsAddress);
                    //setting the sms content or description
                    mSmsItem.setMessageContent(smsContent);

                    //lets add this sms object to our sms list
                    mSmsItemList.add(mSmsItem);

                }
                mSmsAdapter = new SmsAdapter(getActivity(), mSmsItemList);
                mSmsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mSmsRecyclerView.addItemDecoration(new CommonItemSpaceDecoration(5, false));
                mSmsRecyclerView.setAdapter(mSmsAdapter);
            }
        } else {

            // list of SMS items
            List<SmsItem> mSmsItemList = new ArrayList();
            //a SMS Object
            SmsItem mSmsItem;

            Uri uriSms = Uri.parse("content://sms/sent");
            final Cursor cursor = getActivity().getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"}, null, null, null);

            while (cursor.moveToNext()) {
                String smsAddress = cursor.getString(1);
                String smsContent = cursor.getString(3);

                mSmsItem = new SmsItem();

                //setting the sms address or number
                mSmsItem.setNumber(smsAddress);
                //setting the sms content or description
                mSmsItem.setMessageContent(smsContent);

                //lets add this sms object to our sms list
                mSmsItemList.add(mSmsItem);
            }
            mSmsAdapter = new SmsAdapter(getActivity(), mSmsItemList);
            mSmsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mSmsRecyclerView.addItemDecoration(new CommonItemSpaceDecoration(5, false));
            mSmsRecyclerView.setAdapter(mSmsAdapter);
        }
    }

    private Boolean checkReadSmsPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.READ_SMS)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.READ_SMS},
                        READ_SMS_PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.READ_SMS},
                        READ_SMS_PERMISSIONS_REQUEST);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_SMS_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.READ_SMS)
                            == PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                } else {
                    Toast.makeText(getActivity(), "You denied the Read Sms Permission", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}
