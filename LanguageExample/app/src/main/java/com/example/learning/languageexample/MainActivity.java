package com.example.learning.languageexample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.language) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Language")
                    .setMessage("Choose your language")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sharedPreferences = getSharedPreferences("language_choice" , Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString("language" , "english").apply();
                            change();
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sharedPreferences = getSharedPreferences("language_choice" , Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString("language" , "spanish").apply();
                            change();
                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void change() {
        SharedPreferences sharedPreferences = getSharedPreferences("language_choice" , Context.MODE_PRIVATE);
        if("english".equals(sharedPreferences.getString("language" , "english"))) {
            ((TextView) findViewById(R.id.message)).setText("Hello World!");
        } else {
            ((TextView) findViewById(R.id.message)).setText("Hola Mundo!\n");
        }
    }


}
