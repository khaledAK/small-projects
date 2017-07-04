package com.example.khaleda.jobtask.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.khaleda.jobtask.R;
import com.example.khaleda.jobtask.support.ViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(getString(R.string.home));
        initializeUI();
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContactsFragment(), getString(R.string.contacts));
        adapter.addFragment(new NewsFragment(), getString(R.string.news));
        viewPager.setAdapter(adapter);
    }



    public void initializeUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    /// add menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    /// event handling for clicking on option item
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                chooseSettings();
                return true;
            case R.id.logout:
                chooseLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /// on settings chosen
    public void chooseSettings() {
        Intent intent = new Intent(this , SettingsActivity.class);
        startActivity(intent);
    }

    /// on logout chosen
    public void chooseLogout() {
        SharedPreferences userPreference = getSharedPreferences("user_preference" , MODE_PRIVATE);
        userPreference.edit().putBoolean("loggedin" , false).apply();
        Intent intent = new Intent(this , LoginACtivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}