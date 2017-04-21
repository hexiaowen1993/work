package com.fengmi.he.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.fengmi.he.R;

public class MainActivity extends AppCompatActivity {
    int time=1;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           if(msg.what==0){
               if(time>0){
                   time--;
                   handler.sendEmptyMessageDelayed(0,1000);
               }else{
               Intent intent=new Intent(MainActivity.this,Zhu.class);
               startActivity(intent);
                   finish();
                   //移除消息
                   handler.removeMessages(0);


           }

           }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView pic= (ImageView) findViewById(R.id.pic);
        handler.sendEmptyMessageDelayed(0,1000);

    }
}
