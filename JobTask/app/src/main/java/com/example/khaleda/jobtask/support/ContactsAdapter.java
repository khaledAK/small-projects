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
import com.example.khaleda.jobtask.model.ContactModel;

/**
 * Created by khaleda on 01/07/17.
 */

public class ContactsAdapter  extends ArrayAdapter<ContactModel>{
    public ContactsAdapter(Context context , ContactModel[] resource) {
        super(context , R.layout.contact_item , resource);
    }


    /// set view for contact adapter
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.contact_item , parent , false);
        ((TextView)view.findViewById(R.id.name)).setText(getItem(position).getName());
        return view;
    }
}