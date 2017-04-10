package com.example.administrator.hexiaowen20170410;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Circle custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custom = (Circle) findViewById(R.id.custom);
    }
    int mix=0;
    int max=100;
    public void  start(View v){
       custom.setMax(100);
        mix=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
               while(true){
                mix=mix+1;
                   custom.setProgress(mix);
                   try {
                       sleep(50);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   if(mix==100){
                       break;
                   }
               }
            }
        }).start();
    }
    public void  chongzhi(View v){
        custom.setMax(100);
        mix=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                  custom.setProgress(0);
                break;
                }
            }
        }).start();

    }

}
