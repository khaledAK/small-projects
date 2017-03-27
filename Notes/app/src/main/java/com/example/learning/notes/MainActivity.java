package com.example.learning.notes;

import android.bluetooth.le.AdvertiseData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView = (ListView)findViewById(R.id.list_item);
        sharedPreferences = getSharedPreferences("com.example.learning.notes" , Context.MODE_PRIVATE);
        setItems();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, int position, long id) {
                final long idx = id;
                deleteItem(parent , idx);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                editNote((TextView)view);
            }
        });

    }

    public void deleteItem(final AdapterView<?> parent , final long idx) {
        new AlertDialog.Builder(this)
                .setTitle("Are you sure")
                .setMessage("Do you want to delete this note?!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Set<String> set = sharedPreferences.getStringSet("notes" , new HashSet<String>());
                        Set<String>newSet = new HashSet<>();
                        for(String it: set) {
                            if(!it.equals(parent.getItemAtPosition((int)idx).toString()))
                                newSet.add(it);
                        }

                        sharedPreferences.edit().putStringSet("notes" , newSet).apply();
                        sharedPreferences.edit().commit();
                        setItems();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.features , menu);
        return true;
    }

    public void editNote(TextView view) {
        Intent intent = new Intent(this , AddNote.class);
        intent.putExtra("text" , view.getText());
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add) {
            Intent intent = new Intent(this , AddNote.class);
            intent.putExtra("text" , "---");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setItems() {
        Set<String> set =  sharedPreferences.getStringSet("notes" , new HashSet<String>());
        ArrayList<String> array = new ArrayList<>();
        for(String it: set) {
            array.add(it);
        }
        ArrayAdapter adapter = new ArrayAdapter(this , android.R.layout.simple_expandable_list_item_1 , array);
        listView.setAdapter(adapter);
    }

}
