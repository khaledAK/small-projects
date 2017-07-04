package com.example.khaleda.jobtask.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.khaleda.jobtask.R;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        setTitle(getString(R.string.new_details));
        ((TextView) findViewById(R.id.title)).setText(getIntent().getStringExtra("title"));
        ((TextView) findViewById(R.id.body)).setText(getIntent().getStringExtra("body"));
    }


}
