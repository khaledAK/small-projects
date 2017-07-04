package com.example.khaleda.jobtask.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaleda.jobtask.R;

import java.util.Locale;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private TextView language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        language = (TextView)findViewById(R.id.language);
        setLangauge();
    }

    /// set event handling when choosing change language
    public void setLangauge() {
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsActivity.this).
                        setTitle(getString(R.string.change_Language)).
                        setPositiveButton(getString(R.string.arabic), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Locale locale = new Locale("ar");
                                Locale.setDefault(locale);
                                Configuration config = getBaseContext().getResources().getConfiguration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config,
                                        getBaseContext().getResources().getDisplayMetrics());
                                SharedPreferences userPreference = getSharedPreferences("user_preference" , MODE_PRIVATE);
                                userPreference.edit().putString("lang" , "ar").apply();
                            }
                        }).setNegativeButton(getString(R.string.english), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Locale locale = new Locale("en");
                                Locale.setDefault(locale);
                                Configuration config = getBaseContext().getResources().getConfiguration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config,
                                        getBaseContext().getResources().getDisplayMetrics());
                                SharedPreferences userPreference = getSharedPreferences("user_preference" , MODE_PRIVATE);
                                userPreference.edit().putString("lang" , "en").apply();
                            }
                    }).show();
            }
        });
    }
}
