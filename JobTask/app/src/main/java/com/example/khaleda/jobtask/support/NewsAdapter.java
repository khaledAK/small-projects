package com.example.khaleda.jobtask.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.khaleda.jobtask.R;
import com.example.khaleda.jobtask.model.NewsModel;

/**
 * Created by khaleda on 02/07/17.
 */

public class NewsAdapter extends ArrayAdapter<NewsModel> {
    public NewsAdapter(Context context , NewsModel[] resource) {
        super(context , R.layout.news_item, resource);
    }

    /// set view for new adapter
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.news_item, parent , false);
        ((TextView)view.findViewById(R.id.title)).setText(getItem(position).getTitle());
        return view;
    }
}