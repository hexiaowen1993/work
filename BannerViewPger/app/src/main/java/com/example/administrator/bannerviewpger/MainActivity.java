package com.example.administrator.bannerviewpger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
String pic1="https://img04.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg04.sogoucdn.com%2Fapp%2Fa%2F100520024%2F06cd70dfbd2f7a43711673a3234fc6dc&appid=122    ";
    String pic2="https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg03.sogoucdn.com%2Fapp%2Fa%2F100520024%2Fb2aab00016bc908341a96edea3fb5887&appid=122";
    String pic3="https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg02.sogoucdn.com%2Fapp%2Fa%2F100520024%2F2503b95bc272e8357c80335f1b54db61&appid=122";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Banner banner= (Banner) findViewById(R.id.viewpager);
        //设置图片加载器
        banner.setImageLoader(new Image());
        ArrayList<String> list=new ArrayList<String>();
         list.add(pic1);
        list.add(pic2);
        list.add(pic3);

        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
      //  Banner 的监听方法
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, "ssssssss", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
