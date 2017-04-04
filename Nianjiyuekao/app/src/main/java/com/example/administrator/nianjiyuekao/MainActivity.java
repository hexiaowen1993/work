package com.example.administrator.nianjiyuekao;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.nianjiyuekao.Utils.Adapter;
import com.example.administrator.nianjiyuekao.Utils.Bean;
import com.example.administrator.nianjiyuekao.Utils.jiexi;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String[] jieduan = {"小学", "初中", "高中"};
    final String[] xiaoxue = {"三年级", "四年级", "五年级"};
    final String[] chuzhong = {"初一", "初二", "初三"};
    final String[] gaozhong = {"高一", "高二", "高三"};
    String[] json = new String[]{"data1.json", "data2.json", "data3.json"};
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        final Button butt = (Button) findViewById(R.id.button);
        getdata(json[0]);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View pop = View.inflate(MainActivity.this, R.layout.popwindow, null);
                PopupWindow popupWindow = new PopupWindow(pop, ActionBar.LayoutParams.MATCH_PARENT, 300);
                ListView lvjieduan = (ListView) pop.findViewById(R.id.lvjieduan);
                final ListView lvnianji = (ListView) pop.findViewById(R.id.lvnianji);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                lvjieduan.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, jieduan));
                popupWindow.showAsDropDown(butt);
                lvjieduan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            lvnianji.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item, xiaoxue));
                        } else if (position == 1) {
                            lvnianji.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item, chuzhong));

                        } else if (position == 2) {
                            lvnianji.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item, gaozhong));

                        }
                    }
                });
                lvnianji.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        getdata(json[0]);

                        if (lvnianji.getAdapter().getItem(position).equals("初一")) {
                            getdata(json[1]);
                        } else if (lvnianji.getAdapter().getItem(position).equals("高一")) {
                            getdata(json[2]);
                        }
                        return false;
                    }
                });
            }
        });
    }

    private void getdata(String name) {
        InputStream inputStream = getApplicationContext().getClassLoader().getResourceAsStream("assets/" + name);
        String jie = jiexi.jie(inputStream);
        Gson gson=new Gson();
        Bean bean = gson.fromJson(jie, Bean.class);
        ArrayList<Bean.DataBean.NewsInfo> zhuanList =  bean.data.zhuanList;
        lv.setAdapter(new Adapter(MainActivity.this,zhuanList));

    }
}