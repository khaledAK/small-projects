package com.example.exqueueme.gridlayoutdeom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void foo(View view){
        Toast.makeText(getApplicationContext() , view.getResources().getResourceEntryName(view.getId()) + "" , Toast.LENGTH_LONG).show();
    }
}
