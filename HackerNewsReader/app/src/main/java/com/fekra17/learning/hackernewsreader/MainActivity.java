package com.fekra17.learning.hackernewsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener{
    private RecyclerView recyclerView;
    private List<String> urls;
    private List<String> titles;
    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int currentPosition;
    int idsLength;
    String [] storiesId;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }
    public void initRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.news_list);
        urls = new ArrayList<String>();
        titles = new ArrayList<>();
        try {
            String res = (new APICall()).execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").get();
            storiesId = res.split(", ");
            StringBuilder stringBuilder = new StringBuilder(storiesId[0]);
            stringBuilder.delete(0 , 2);
            storiesId[0] = stringBuilder.toString();
            stringBuilder = new StringBuilder(storiesId[storiesId.length - 1]);
            stringBuilder.delete(storiesId[storiesId.length - 1].length() - 3 , storiesId[storiesId.length - 1].length() + 1);
            storiesId[storiesId.length - 1] = stringBuilder.toString();
            idsLength = storiesId.length;
            for (currentPosition = 0; currentPosition < Math.min(10 , idsLength); currentPosition ++) {
                String story =
                        (new APICall()).execute("https://hacker-news.firebaseio.com/v0/item/" + storiesId[currentPosition]
                                + ".json?print=pretty").get();
                JSONObject jsonObject = new JSONObject(story);
                if(jsonObject.has("title") && jsonObject.has("url")) {
                    titles.add(jsonObject.getString("title"));
                    urls.add(jsonObject.getString("url"));
                }
            }
            recyclerViewAdapter = new RecyclerViewAdapter(titles , this , this);
            final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
            {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy)
                {
                    if(dy > 0) //check for scroll down
                    {
                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisiblesItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();


                        if (!loading)
                        {
                            if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                            {
                                updateRecyclerView();
                            }
                        }
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateRecyclerView() {
        loading = true;
        for (int first = currentPosition; currentPosition < Math.min(first + 5 , idsLength); currentPosition ++) {
           try {
               String story =
                       (new APICall()).execute("https://hacker-news.firebaseio.com/v0/item/" + storiesId[currentPosition]
                               + ".json?print=pretty").get();
               JSONObject jsonObject = new JSONObject(story);
               if (jsonObject.has("title") && jsonObject.has("url")) {
                   titles.add(jsonObject.getString("title"));
                   urls.add(jsonObject.getString("url"));
               }
           } catch (JSONException e) {
               e.printStackTrace();
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }
        }
        recyclerViewAdapter.notifyDataSetChanged();
        loading = false;
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(this , Artical.class);
        intent.putExtra("url" , urls.get(position));
        startActivity(intent);
    }
}


interface RecyclerViewClickListener {
    public void recyclerViewListClicked(View v, int position);
}
