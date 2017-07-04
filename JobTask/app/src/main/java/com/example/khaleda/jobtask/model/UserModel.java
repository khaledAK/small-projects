package com.example.khaleda.jobtask.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by khaleda on 01/07/17.
 */

/// user model
public class UserModel {
    private String email;
    private String password;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    public UserModel(Context context , String email , String password) {
        this.email = email;
        this.password = password;
        this.context = context;
    }

    public SQLiteDatabase getTable() {
        if(sqLiteDatabase == null) {
            sqLiteDatabase = context.openOrCreateDatabase("JOBTASK", Context.MODE_PRIVATE, null);
            String query = "CREATE TABLE IF NOT EXISTS user (_id INTEGER PRIMARY KEY , email VARCHAR(40) UNIQUE" +
                    ", password VARCHAR(25))";
            sqLiteDatabase.execSQL(query);
        }
        return sqLiteDatabase;
    }

    public int save() {
        try {
            String query = "INSERT INTO user(email , password) VALUES ('" + this.email + "' , '" + this.password +"')";
            getTable().execSQL(query);
        } catch (Exception e){
            return -1;
        }
        return search();
    }

    public int search() {
        int id = -1;
        try {
            String query = "SELECT * FROM user WHERE email = '" + this.email + "' and password = '" + this.password + "'";
            Cursor cursor = getTable().rawQuery(query , null);
            int idIndex = cursor.getColumnIndex("_id");
            int emailIndex = cursor.getColumnIndex("email");
            cursor.moveToFirst();
            if (cursor != null) {
                id = cursor.getInt(idIndex);
                cursor.moveToNext();
            }
        } catch(Exception e) {
            return -1;
        }

        return id;
    }
}