package com.sherlock.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.sherlock.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.cv_sms)
    CardView smsCardView;
    @BindView(R.id.cv_phone_contacts)
    CardView phoneContactsCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cv_sms, R.id.cv_phone_contacts})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.cv_sms:
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.cv_phone_contacts:
                Intent intent1 = new Intent(HomeActivity.this, ContactSyncActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
