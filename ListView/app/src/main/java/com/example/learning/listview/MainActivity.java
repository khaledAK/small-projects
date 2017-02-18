package com.example.learning.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);

        ArrayList<String> array = new ArrayList<>();
        array.add("khaled");
        array.add("Ahmed");
        array.add("mahmoud");
        array.add("khaled");
        array.add("Ahmed");
        array.add("mahmoud");
        array.add("khaled");
        array.add("Ahmed");
        array.add("mahmoud");
        array.add("khaled");
        array.add("Ahmed");
        array.add("mahmoud");
        array.add("khaled");
        array.add("Ahmed");
        array.add("mahmoud");
        array.add("khaled");
        array.add("Ahmed");
        array.add("mahmoud");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.itme , array);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /// parent is the list view itself
                /// view represent the item itself
                ///position is the number of item
                /// id is the same as position but differ when i don't use arrayadapter
            }
        });

    }
}
