package com.example.learning.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        /// how runnable work with handler this code will be excuted every second
//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("here" , "here");
//                handler.postDelayed(this , 1000);
//            }
//        };
//        handler.post(run);


        ///this is the count down timer that count 10000 ms until finish and every tick excuted after 1000 ms

//        new CountDownTimer(10000 , 1000) {
//            public void onTick(long millisecondsUntilDone){
//                Log.i("count" , millisecondsUntilDone + "");
//            }
//            public void onFinish(){
//                Log.i("done" , "ddone");
//            }
//        }.start();
    }
}
