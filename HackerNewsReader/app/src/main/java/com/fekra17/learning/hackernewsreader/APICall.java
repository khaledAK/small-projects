package com.fekra17.learning.hackernewsreader;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by khaledA on 4/20/2017.
 */

public class APICall extends AsyncTask<String , Void , String> {
    @Override
    public String doInBackground(String ... params) {
        try {
            StringBuilder result = new StringBuilder("");
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();
            while(data != -1) {
                result.append((char)data);
                data = inputStreamReader.read();
            }
            return result.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
