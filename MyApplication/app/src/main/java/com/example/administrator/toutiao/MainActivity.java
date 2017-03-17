package com.example.administrator.toutiao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

int time=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView huanying= (ImageView) findViewById(R.id.huanying);
        huanying.setScaleType(ImageView.ScaleType.FIT_XY);
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,Zhu.class));
            }
        };
        timer.schedule(task,2000);
    }
}
