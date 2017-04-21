package com.example.learning.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            /// it's all about sql learn sql
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("users" , MODE_PRIVATE , null);
//            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR , age INT(3))");
//            sqLiteDatabase.execSQL("INSERT INTO users (name , age) VALUES ('Khaled' , 21)");
//            sqLiteDatabase.execSQL("INSERT INTO users (name , age) VALUES ('Khaled' , 21)");
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users" , null);
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("here", cursor.getString(nameIndex) + "  " + cursor.getInt(ageIndex));
                cursor.moveToNext();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
