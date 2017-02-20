package com.example.learning.eggtimer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static SeekBar seekBar;
    int progressInTimer;
    CountDownTimer counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar)findViewById(R.id.timer_limit);
        seekBar.setMax(600);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int h = progress / 60;
                progress %= 60;
                String s = h + ":" + (progress%60<10?"0":"") + progress%60;
                ((TextView)findViewById(R.id.timer_text)).setText(s);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ((Button)findViewById(R.id.go_stop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = (Button)v;
                if(((Button) v).getText().equals("Go")) {
                   ((Button) v).setText("Stop");
                   seekBar.setEnabled(false);
                   progressInTimer = seekBar.getProgress();
                   counter = new CountDownTimer((progressInTimer) * 1000 , 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            progressInTimer --;
                            int h = progressInTimer / 60;
                            String s = h + ":" + (progressInTimer%60<10?"0":"") + progressInTimer%60;
                            ((TextView)findViewById(R.id.timer_text)).setText(s);
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.tic);
                            mediaPlayer.start();
                        }

                        @Override
                        public void onFinish() {
                            foo();
                            seekBar.setProgress(0);
                            ((TextView)findViewById(R.id.timer_text)).setText("0:00");
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.sound);
                            mediaPlayer.start();

                        }
                       public void foo() {
                           ((Button) findViewById(R.id.go_stop)).setText("Go");
                           seekBar.setEnabled(true);
                       }
                    };
                    counter.start();

                } else {
                    ((Button) v).setText("Go");
                    seekBar.setEnabled(true);
                    seekBar.setProgress(progressInTimer);

                    counter.cancel();
                }
            }
        });
    }
}
