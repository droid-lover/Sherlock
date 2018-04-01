package com.sherlock.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.sherlock.R;

import butterknife.ButterKnife;


public class SplashActivity extends Activity {

    private int SPLASH_TIME_OUT = 2100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initializeView();


    }

    private void initializeView() {
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

                                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                                  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                          startActivity(intent);
                                      }

                                  }
                , SPLASH_TIME_OUT);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
