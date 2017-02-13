package com.example.exqueueme.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    AudioManager audioManager , audioManager1;
    static boolean ok = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext() , "ok = " + ok , Toast.LENGTH_LONG).show();
        mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.sounddemo);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volumeseeker = (SeekBar)findViewById(R.id.seekbar);
        volumeseeker.setMax(maxVolume);
        volumeseeker.setProgress(curVolume);
        volumeseeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC , progress , 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar musicseeker = (SeekBar)findViewById(R.id.seekbar1);
        audioManager1 = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        musicseeker.setMax(mediaPlayer.getDuration());
        musicseeker.setProgress(0);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ok = false;
                musicseeker.setProgress(mediaPlayer.getCurrentPosition());
            }
        } , 0 /*number of second before starting timer*/ , 1000/*number of milli seconds between succefive call of the timer*/);
        musicseeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(ok)
                 mediaPlayer.seekTo(progress);
                foo();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
            public void foo() {
                /// ok here to handle syncronization between audio and time
                ok = true;
            }
        });

    }

    public void on(View view){
        mediaPlayer.start();
        // search in its functionality
    }

    public void off(View view){
        mediaPlayer.pause();
    }
}
