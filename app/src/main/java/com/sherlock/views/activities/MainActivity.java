package com.sherlock.views.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.sherlock.R;
import com.sherlock.adapters.HomeTabAdapter;
import com.sherlock.adapters.SmsAdapter;
import com.sherlock.models.SmsItem;
import com.sherlock.utils.CommonItemSpaceDecoration;
import com.sherlock.views.fragments.InboxFragment;
import com.sherlock.views.fragments.SentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private HomeTabAdapter mHomeTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeViews();
    }

    private void initializeViews() {

        getSupportActionBar().setElevation(0);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mHomeTabAdapter = new HomeTabAdapter(getSupportFragmentManager());
        mHomeTabAdapter.addFragment(new InboxFragment(), "INBOX");
        mHomeTabAdapter.addFragment(new SentFragment(), "SENT");
        mViewPager.setAdapter(mHomeTabAdapter);
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
