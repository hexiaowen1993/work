package com.example.administrator.circle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Circle circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circle = (Circle) findViewById(R.id.custom);
    }
  // String mix = 1234+"";
   int mix = 0;
    //随机数
   /* public void start(View v){

        mix = 1234+"";
        new Thread(new Runnable() {
            @Override
            public void run() {
                mix = randomText();
             circle.setProgress(Integer.parseInt(mix));

            }
        }).start();
    }*/
    //进度条
    public void start(View v) {

        mix = 0;
        circle.setMax(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mix = mix + 1;
                    circle.setProgress(mix);
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (mix == 100) {
                        break;
                    }

                }
            }


        }).start();
    }
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
