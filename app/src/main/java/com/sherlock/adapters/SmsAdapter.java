package com.sherlock.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sherlock.R;
import com.sherlock.holders.SmsViewHolder;
import com.sherlock.models.SmsItem;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsViewHolder> {

    private List<SmsItem> mSmsItems;
    private Context mContext;

    LayoutInflater layoutInflater;

    public SmsAdapter(Context context, List<SmsItem> smsList) {
        this.mContext = context;
        this.mSmsItems = smsList;
    }

    @Override
    public SmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_sms_row, parent, false);
        return new SmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SmsViewHolder holder, int position) {

        try {
            if (mSmsItems.get(position).getNumber() != null) {
                holder.mSmsNumber.setText(mSmsItems.get(position).getNumber());
            }
            if (mSmsItems.get(position).getMessageContent() != null) {
                holder.mSmsContent.setText(mSmsItems.get(position).getMessageContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mSmsItems.size();
    }
}