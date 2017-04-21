package com.example.learning.customlistview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


   DataModel data[] = {new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
           , new DataModel("khaled" , "hhhhhh") , new DataModel("Ahmed" , "hhhh")
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomAdapter customAdapter = new CustomAdapter(this , data);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(customAdapter);

    }
}



class  CustomAdapter extends ArrayAdapter<DataModel> {


    public CustomAdapter(Context context , DataModel[] resource) {
        super(context , R.layout.custom_row , resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_row , parent , false);
        ((TextView)view.findViewById(R.id.name)).setText(getItem(position).name);
        ((TextView)view.findViewById(R.id.desc)).setText(getItem(position).desc);
        return view;
    }
}


class DataModel {
    String name;
    String desc;
    DataModel(String n , String d) {
        name = n;
        desc = d;
    }
}