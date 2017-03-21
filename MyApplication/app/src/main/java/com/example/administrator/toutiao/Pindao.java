package com.example.administrator.toutiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/19.
 */

public class Pindao extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biaoti);
       ImageButton ima_xx= (ImageButton) findViewById(R.id.ima_xx);
        GridView pindao= (GridView) findViewById(R.id.pindao_grid);
       GridView tuijian= (GridView) findViewById(R.id.tuijian_grid);
       ArrayList<String> list=new ArrayList<String>();
        String[] str=new String[]{
                "电影", "消息", "教育", "社会", "娱乐", "科技", "汽车", "体育", "财经","彩票"
        };
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }

         String[] tui=new String[]{
                 "房产", "足球", "笑话", "游戏", "时尚", "情感", "精选", "电台", "数码","旅游","手机","博客","家居",
                 "暴雪游戏","亲子","CBA"
         };
        ArrayList<String> list2=new ArrayList<String>();
        tuijian.setAdapter(new ArrayAdapter<String>(Pindao.this,android.R.layout.simple_list_item_1,tui));
        pindao.setAdapter(new ArrayAdapter<String>(Pindao.this,android.R.layout.simple_list_item_1,str));
        ima_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pindao.this,Zhu.class));
                finish();
            }
        });

    }



}
