package com.example.learning.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class AddNote extends AppCompatActivity {

    EditText editText;
    SharedPreferences sharedPreferences;
    String oldNote;
    @Override
    public void onBackPressed() {
        if(!editText.getText().toString().equals("")) {
            Set<String> set = sharedPreferences.getStringSet("notes" , new HashSet<String>());
            Set<String>newSet = new HashSet<>();
            for(String it: set) {
                if(!it.equals(oldNote))
                    newSet.add(it);
            }

            newSet.add(editText.getText().toString());
            sharedPreferences.edit().putStringSet("notes" , newSet).apply();
            sharedPreferences.edit().commit();
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editText = (EditText)findViewById(R.id.text);
        oldNote = getIntent().getStringExtra("text");
        Log.i("mmm" , oldNote);
        if(!oldNote.equals("---")) {
            editText.setText(oldNote);
        }
        sharedPreferences = getSharedPreferences("com.example.learning.notes" , Context.MODE_PRIVATE);
    }


}
