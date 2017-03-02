package com.example.learning.assignment3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView profilePicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profilePicture = (ImageView)findViewById(R.id.profile_picture);
        profilePicture.setImageResource(R.mipmap.ic_launcher);
        new loadImage().execute
                ("https://static.pexels.com/photos/60100/pexels-photo-60100.jpeg");
    }

    class loadImage extends AsyncTask<String , Void , Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bm = null;
            try {
                URL aURL = new URL(params[0]);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
            }
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            profilePicture.setImageBitmap(image);
        }
    }
}
