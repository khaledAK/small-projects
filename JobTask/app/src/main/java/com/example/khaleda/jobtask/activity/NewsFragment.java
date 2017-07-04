package com.example.khaleda.jobtask.activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khaleda.jobtask.R;
import com.example.khaleda.jobtask.model.NewsModel;
import com.example.khaleda.jobtask.support.ContactsAdapter;
import com.example.khaleda.jobtask.support.DownloadJson;
import com.example.khaleda.jobtask.support.NewsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by khaleda on 02/07/17.
 */

public class NewsFragment extends Fragment {

    private List<NewsModel> data;
    private ListView listView;
    private ContactsAdapter contactsAdapter;
    private final String DATA_URL= "https://jsonplaceholder.typicode.com/posts";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        listView = (ListView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeContacts();
        readData();
        setListView();
    }



    public void initializeContacts() {
        data = new ArrayList<>();
    }


    /// get the json file from class download json and parse the data
    public void readData() {
        try {
            SharedPreferences userPreference = getActivity().getSharedPreferences("user_preference" , Context.MODE_PRIVATE);
            int id = userPreference.getInt("id" , -1);
            JSONArray returnData = new JSONArray(new DownloadJson().execute(DATA_URL).get());
            for(int i = 0; i < returnData.length(); i ++) {
                JSONObject jsonObject = returnData.getJSONObject(i);
                if(jsonObject.getInt("userId") == id)
                     data.add(new NewsModel(jsonObject.getString("title") , jsonObject.getString("body")));
            }
        } catch (JSONException e) {
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }


    /// set list view  and event handling for clicking on new item
    public void setListView() {
        NewsAdapter customAdapter = new NewsAdapter(getContext() , data.toArray(new NewsModel[data.size()]));
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity() , NewActivity.class);
                intent.putExtra("title" , data.get(position).getTitle());
                intent.putExtra("body"  , data.get(position).getBody());
                startActivity(intent);
            }
        });
    }
}
