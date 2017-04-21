package com.fengmi.he.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.fengmi.he.FragmentPakage.Fragment_fenlei;
import com.fengmi.he.R;
import com.fengmi.he.fragment_fenlei.Fragment_gongxiao;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/13.
 */

public class FenleiActivity extends FragmentActivity {

    private String[] title;
    private ArrayList<Fragment> gongxiaolist;
    private int name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fr_gongxiao);
        final Intent intent = getIntent();
        name = intent.getIntExtra("name", -1);
        ImageView butt = (ImageView) findViewById(R.id.button);
        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        ViewPager pager = (ViewPager) findViewById(R.id.gongxiao_page);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten1=new Intent(FenleiActivity.this, Zhu.class);
                startActivity(inten1);
            }
        });
        title = new String[]{
                "补水保湿", "美白提亮", " 控油祛痘", " 实惠套装", "中性肤质", "敏感肤质", " 贴士面膜", "洁面乳"
        };
        String[] url = {
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17", //补水保湿
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=59154&encode=4f90b7d8723b9819b90acf2a845abe98&category_id=18",
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=59154&encode=4f90b7d8723b9819b90acf2a845abe98&category_id=18",
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=47920&encode=d4b0bb7403d31c66f22c33397ad896e3&category_id=33",
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=13",
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=13819&encode=d58e53c4b9e24bd5ba276ba68f8e98ec&category_id=17",
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=83560&encode=3108ed0b9a42c1e160b2912a78692263&category_id=9",
                "http://m.yunifang.com/yunifang/mobile/goods/getall?random=24799&encode=ebe717bb1c72e105ca7d55d3ce463bab&category_id=24"
        };
        gongxiaolist = new ArrayList<Fragment>();
        for (int i = 0; i < title.length; i++) {
            Fragment_gongxiao gongxiao = new Fragment_gongxiao();
            gongxiao.setUrl(url[i]);
            gongxiaolist.add(gongxiao);
        }

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tab.setupWithViewPager(pager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (name == 0) {
            pager.setCurrentItem(0);
        } else if (name == 1) {
            pager.setCurrentItem(1);
        } else if (name == 2) {
            pager.setCurrentItem(2);
        } else if (name == 3) {
            pager.setCurrentItem(3);
        } else if (name == 4) {
            pager.setCurrentItem(4);
        } else if (name == 5) {
            pager.setCurrentItem(5);
        } else if (name == 6) {
            pager.setCurrentItem(6);
        } else if (name == 7) {
            pager.setCurrentItem(7);
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return gongxiaolist.get(position);
        }

        @Override
        public int getCount() {
            return gongxiaolist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
