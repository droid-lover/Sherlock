package com.sherlock.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sherlock.R;
import com.sherlock.holders.ContactsHolder;
import com.sherlock.holders.SmsViewHolder;
import com.sherlock.models.SmsItem;
import com.sherlock.models.UserContact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsHolder> {

    private List<UserContact> mContactList;
    private Context mContext;

    public ContactsAdapter(Context context, List<UserContact> mContactList) {
        this.mContext = context;
        this.mContactList = mContactList;
    }

    @Override
    public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contact_row, parent, false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsHolder holder, int position) {

        try {
            if (mContactList.get(position).getUsername() != null) {
                holder.mContactNameTextView.setText(mContactList.get(position).getUsername());
            }
            if (mContactList.get(position).getUserphone() != null) {
                holder.mContactNumberTextView.setText(mContactList.get(position).getUserphone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}