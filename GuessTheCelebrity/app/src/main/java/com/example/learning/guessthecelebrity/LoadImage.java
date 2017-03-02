package com.example.learning.guessthecelebrity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by khaledA on 3/2/2017.
 */

public class LoadImage extends AsyncTask<String , Void , Bitmap> {
    private ImageView image;
    public LoadImage(Activity applicationActivity) {
        image = (ImageView)applicationActivity.findViewById(R.id.celebrity_image);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap image = null;
        try {
            URL url = new URL(params[0]);
            URLConnection urlConnection = url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedInputStream buffer = new BufferedInputStream(in);
            image = BitmapFactory.decodeStream(buffer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
    }
}
