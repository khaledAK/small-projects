package com.example.khaleda.jobtask.model;


import android.content.Context;
import android.util.Log;

import com.example.khaleda.jobtask.R;

/**
 * Created by khaleda on 01/07/17.
 */


/// model for contact
public class ContactModel {

    private String name;
    private String phoneNumber;

    public ContactModel(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }
}
