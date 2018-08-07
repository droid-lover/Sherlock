package com.sherlock.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sherlock.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_contact_name)
    public TextView mContactNameTextView;

    @BindView(R.id.tv_contact_number)
    public TextView mContactNumberTextView;

    public ContactsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
