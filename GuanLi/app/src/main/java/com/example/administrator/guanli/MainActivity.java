package com.example.administrator.guanli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import weeks.drag.com.draglibary.ManagerActivity;
import weeks.drag.com.draglibary.bean.ChannelItem;
import weeks.drag.com.draglibary.bean.ChannelManage;

public class MainActivity extends AppCompatActivity {
String s = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490711304864&di=73854e0942ba2450e870bead1b6dc7de&imgtype=jpg&src=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2Fa686c9177f3e6709a81d273e3ec79f3df9dc55d5.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i <10 ; i++) {
            //第一个i+2代表下标
            ChannelItem item =new ChannelItem(i+2,"哈哈",i+2,1);
            ChannelItem items =new ChannelItem(i+10,"嘻嘻",i+10,0);
            ChannelManage manage = new ChannelManage(item,items);
        }
        ImageView iv = (ImageView) findViewById(R.id.src);
        Picasso.with(this).load(s).into(iv);
        findViewById(R.id.te).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ManagerActivity.class));

            }
        });

    }
}
