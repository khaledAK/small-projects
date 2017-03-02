package com.example.learning.guessthecelebrity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    final private String PAGE_LINK = "http://www.posh24.se/kandisar";
    private ArrayList<String> imagesUrl;
    private ArrayList<String> names;
    private Random random;
    private LoadImage loadImage;
    private Button []buttons;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        newGuess();
    }

    public void selectChoice(View view){
        String res = "Incorrect";
        if(((Button) view).getText().equals(names.get(index))) {
            res = "Correct";
        }
        Toast.makeText(this , res , Toast.LENGTH_SHORT).show();
        newGuess();
    }

    public void newGuess(){
        int imageIdx = random.nextInt(99);
        index = imageIdx;
        Log.i("index" , "" + imageIdx);
        loadImage = new LoadImage(this);
        loadImage.execute(imagesUrl.get(imageIdx));

        Set<Integer> indexs = new HashSet<>();
        indexs.add(imageIdx);
        while(indexs.size() < 4) {
            indexs.add(random.nextInt(99));
        }
        int []arr = {0 , 1 , 2 , 3};
        shuffleArray(arr);
        int i = 0;
        for (int it:indexs) {
            buttons[arr[i]].setText(names.get(it));
            i++;
        }
    }
    static void shuffleArray(int[] ar)  {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public void init(){
        LoadWebContent loadWebContent = new LoadWebContent();
        imagesUrl = new ArrayList<>();
        names = new ArrayList<>();
        random = new Random();
        buttons = new Button[4];
        buttons[0] = (Button)findViewById(R.id.choice_1);
        buttons[1] = (Button)findViewById(R.id.choice_2);
        buttons[2] = (Button)findViewById(R.id.choice_3);
        buttons[3] = (Button)findViewById(R.id.choice_4);
        String content = "";
        try {
            content = loadWebContent.execute(PAGE_LINK).get();
            String[] splitResult = content.split("<div class=\"sidebarContainer\">");
            Pattern p = Pattern.compile("<img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);
            while (m.find()) {
                imagesUrl.add(m.group(1));
            }
            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);
            while (m.find()) {
                names.add(m.group(1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
