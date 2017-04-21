package com.fekra17.learning.hackernewsreader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.List;

/**
 * Created by khaledA on 4/20/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataHolder> {
    private List<String> data;

    private Context context;
    private static RecyclerViewClickListener itemListener;
    public RecyclerViewAdapter(List data , Context context , RecyclerViewClickListener itemListener) {
        this.data = data;
        this.context = context;
        this.itemListener = itemListener;
    }
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item , parent , false);
        DataHolder dataHolder = new DataHolder(itemView);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        String data = this.data.get(position);
        holder.textView.setText(data);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView;
        public DataHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.hacker_new);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getPosition());
        }
    }


}
