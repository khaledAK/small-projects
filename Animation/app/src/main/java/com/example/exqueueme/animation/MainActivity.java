package com.example.exqueueme.animation;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeImage(View view) {
        ImageView imageView = (ImageView)findViewById(R.id.animation);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Toast.makeText(getApplicationContext() , imageView + " " + width , Toast.LENGTH_LONG).show();
        imageView.animate().scaleXBy(width/imageView.getWidth() - 1);


        ImageView imageView2 = (ImageView)findViewById(R.id.image);
        imageView2.animate().alpha(1f);
    }
    public void changeImage2(View view) {
        ImageView imageView = (ImageView)findViewById(R.id.animation);
        imageView.animate().alpha(1f);
        imageView.animate().translationXBy(-10f);
        ImageView imageView2 = (ImageView)findViewById(R.id.image);
        imageView2.animate().alpha(0f);
    }
}
