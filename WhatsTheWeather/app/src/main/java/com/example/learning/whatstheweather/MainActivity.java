package com.example.learning.whatstheweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView weather;
    private EditText cityName;
    final private String API_URL = "http://api.openweathermap.org/data/2.5/weather?appid=9dc9e56ace548fb9042b05e2c626b127&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = (EditText)findViewById(R.id.city_name);
        weather = (TextView)findViewById(R.id.weather_data);
    }

    public void showWeather(View view) {
        String name = cityName.getText().toString();
        APIWeather apiWeather = new APIWeather();
        JSONObject weatherData = null;
        try {
            weatherData = apiWeather.execute(API_URL + name).get();
            setWeatherData(weatherData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void setWeatherData(JSONObject weatherData){
        String res = "";
        try {
            res = weatherData.getString("Error");
            weather.setText(res);
            return;
        } catch (JSONException e) {
            try {
                JSONArray arr = new JSONArray(weatherData.getString("weather"));
                for(int i = 0; i < arr.length(); i ++) {
                    weatherData = arr.getJSONObject(i);
                    res += weatherData.getString("main") + ":" + weatherData.getString("description") + "\n";
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        weather.setText(res);
    }

    class APIWeather extends AsyncTask<String , Void , JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            URL url = null;
            JSONObject jsonObject = null;
            try {
                String str = "";
                url = new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader buffer = new InputStreamReader(in);
                int data = buffer.read();
                while(data != -1) {
                    str += (char)data;
                    data = buffer.read();
                }
                jsonObject = new JSONObject(str);
            } catch (MalformedURLException e) {
                try {
                    return new JSONObject("{\"Error\":\"Not Found City\"}");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                try {
                    return new JSONObject("{\"Error\":\"Not Found City\"}");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            } catch (JSONException e) {
                try {
                    return new JSONObject("{\"Error\":\"Not Found City\"}");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            return jsonObject;
        }
    }
}
