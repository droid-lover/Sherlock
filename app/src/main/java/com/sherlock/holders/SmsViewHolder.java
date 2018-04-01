package com.sherlock.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sherlock.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.sms_number)
    public TextView mSmsNumber;

    @BindView(R.id.sms_content)
    public TextView mSmsContent;

    public SmsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
