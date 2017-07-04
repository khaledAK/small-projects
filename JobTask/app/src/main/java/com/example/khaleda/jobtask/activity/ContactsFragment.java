package com.example.khaleda.jobtask.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khaleda.jobtask.R;
import com.example.khaleda.jobtask.model.ContactModel;
import com.example.khaleda.jobtask.support.ContactsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaleda on 01/07/17.
 */

public class ContactsFragment extends Fragment{

    private List<ContactModel> contacts;
    private ListView listView;
    private ContactsAdapter contactsAdapter;
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
        requestPermissions();
        if(android.os.Build.VERSION.SDK_INT < 23) {
            readContacts();
            setListView();
        }
    }


    /// request for permission contacts
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                    setListView();
                } else {

                }
                return;
            }
        }
    }


    public void initializeContacts() {
        contacts = new ArrayList<>();
    }


    /// requestion permission for read contacts
    public void requestPermissions() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 999);
            return;
        }
    }

    /// read contacts
    public void readContacts() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                String mobileNumber = getString(R.string.mobile_number_not_found);
                if (cursor.getInt(cursor.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        mobileNumber = phoneNo;
                    }
                    pCur.close();
                }
                contacts.add(new ContactModel(name , mobileNumber));
            }
        }
    }

    /// set list view and set event handling for contact item click
    public void setListView() {
        ContactsAdapter customAdapter = new ContactsAdapter(getContext() , contacts.toArray(new ContactModel[contacts.size()]));
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity() , ContactActivity.class);
                intent.putExtra("name" , contacts.get(position).getName());
                intent.putExtra("phoneNumber" , contacts.get(position).getPhoneNumber());
                startActivity(intent);
            }
        });
    }
}
