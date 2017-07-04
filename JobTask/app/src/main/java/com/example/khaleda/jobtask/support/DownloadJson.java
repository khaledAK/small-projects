package com.example.khaleda.jobtask.support;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by khaleda on 02/07/17.
 */

public class DownloadJson  extends AsyncTask<String , Void , String> {


        /// download json file based on url comes in params
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            HttpURLConnection request = null;
            InputStream inStream = null;
            try {
                URL reqURL = new URL(params[0]);
                request = (HttpURLConnection) (reqURL.openConnection());
                request.addRequestProperty("content-Type", "application/x-www-form-urlencoded");
                request.setConnectTimeout(1000);
                request.connect();

                inStream = request.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
                String line ="";
                StringBuilder responseOutput = new StringBuilder();
                while((line = bufferedReader.readLine()) != null) {
                    responseOutput.append(line);
                }
                result = responseOutput.toString();
                return result;
            } catch (IOException e) {
            }
            return "[]";
        }
}