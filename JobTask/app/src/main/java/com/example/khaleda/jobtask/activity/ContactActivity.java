package com.example.khaleda.jobtask.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.khaleda.jobtask.R;

public class ContactActivity extends AppCompatActivity {
    private TextView mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setTitle(getIntent().getStringExtra("name"));
        setMobileNumber();
    }

    /// event mobile number and checks if exsit or not to add call
    public void setMobileNumber() {
        mobileNumber = ((TextView) findViewById(R.id.mobile_number));
        mobileNumber.setText(getIntent().getStringExtra("phoneNumber"));
        if (mobileNumber.getText().toString().equals(getString(R.string.mobile_number_not_found))) {
            ((ImageButton) findViewById(R.id.call_icon)).setVisibility(View.GONE);
        }
    }

    /// event handling for clicking on call button
    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobileNumber.getText().toString()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }
}
