package com.example.learning.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    TextView [][]answers;
    CountDownTimer timer;
    TextView equation;
    TextView score;
    TextView finalScore;
    TextView timerText;
    int progress;
    int result;
    int total;
    int first;
    int second;
    int limit;
    int []ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answers = new TextView[2][2];
        answers[0][0] = (TextView)findViewById(R.id.zero_zero);
        answers[0][1] = (TextView)findViewById(R.id.zero_one);
        answers[1][0] = (TextView)findViewById(R.id.one_zero);
        answers[1][1] = (TextView)findViewById(R.id.one_one);
        equation = (TextView)findViewById(R.id.equation_text);
        score = (TextView)findViewById(R.id.score_text);
        finalScore = (TextView)findViewById(R.id.final_score);
        timerText = (TextView) findViewById(R.id.timer_text);
        ans = new int[4];
    }


    public void startGame(View view) {
        view.setVisibility(View.GONE);
        TextView finalScore = (TextView) findViewById(R.id.final_score);
        Button newStart = (Button)findViewById(R.id.newStart);
        newStart.setVisibility(View.INVISIBLE);
        finalScore.setVisibility(View.INVISIBLE);
        LinearLayout game = (LinearLayout)findViewById(R.id.game);
        game.setVisibility(View.VISIBLE);
        run();
    }

    public void chooseAnswer(View view) {
        finalScore.setVisibility(View.VISIBLE);
        int answer = Integer.parseInt(((TextView) view).getText().toString());
        if(answer == ans[0]) { // correct
            result++;
            total++;
            finalScore.setText("Correct");
            limit += 20;
        } else {
            total++;
            finalScore.setText("Incorrect");
        }
        score.setText(result + "/" + total);
        newState();
    }
    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void newState() {
        Random r = new Random();
        first =  r.nextInt(limit);
        second = r.nextInt(limit);
        ans[0] = first + second;
        for(int i = 1; i < 4; i ++)
            ans[i] = ans[0] + r.nextInt(limit + limit) - limit;
        Log.i("here" , ans[0] + " " + ans[1] + " " + ans[2] + " " + ans[3]);
        equation.setText(first + " + " + second);
        score.setText(result + "/" + total);
        boolean []arr = new boolean[4];
        int []f = {0 , 1 , 0 , 1};
        int []s = {0 , 0 , 1 , 1};
        int []idx = {0 , 1 , 2 , 3};
        shuffleArray(idx);
        for(int i = 0; i < 4; i ++) {
           answers[f[i]][s[i]].setText(ans[idx[i]] + "");
        }
    }

    public void run() {
        for(int i = 0; i < 2; i ++) {
            for(int j = 0;j < 2; j ++) {
                answers[i][j].setClickable(true);
            }
        }
        total = result = 0;
        limit = 20;
        newState();
        timerText.setText("30s");
        progress = 30;
        timer = new CountDownTimer(progress * 1000 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progress--;
                timerText.setText(progress + "s");
            }

            @Override
            public void onFinish() {
                for(int i = 0; i < 2; i ++) {
                    for(int j = 0;j < 2; j ++) {
                        answers[i][j].setClickable(false);
                    }
                }
                timerText.setText("0s");
                finalScore.setText("your score: " + score.getText());
                Button newStart = (Button)findViewById(R.id.newStart);
                newStart.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
