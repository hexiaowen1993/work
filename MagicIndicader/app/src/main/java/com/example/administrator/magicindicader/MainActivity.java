package com.example.administrator.magicindicader;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MagicIndicator magic;
    private ViewPager page;
    private ArrayList<String> title;
    private ArrayList<ImageView> ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        magic = (MagicIndicator) findViewById(R.id.macgic);
        page = (ViewPager) findViewById(R.id.page);
        //导航管理
        CommonNavigator navigator=new CommonNavigator(MainActivity.this);
        navigator.setScrollPivotX(0.65f);
        String[] str={
                "a","b","c"
        };
        title = new ArrayList<String>();
        for (int i = 0; i <str.length ; i++) {
            title.add(str[i]);
        }
        ima = new ArrayList<ImageView>();
        ImageView im=new ImageView(MainActivity.this);
        im.setImageResource(R.mipmap.ic_launcher);
        ImageView im1=new ImageView(MainActivity.this);
        im1.setImageResource(R.mipmap.ic_launcher);
        ImageView im2=new ImageView(MainActivity.this);
        im2.setImageResource(R.mipmap.ic_launcher);
        ima.add(im);
        ima.add(im1);
        ima.add(im2);
        MyAdapter adapter=new MyAdapter();
        page.setAdapter(adapter);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return title.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView=new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(title.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"));
               simplePagerTitleView.setSelectedColor(Color.parseColor("#00c853"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        page.setCurrentItem(index);
                    }
                });
                return  simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator=new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context,6));
                indicator.setLineWidth(UIUtil.dip2px(context,10));
                indicator.setRoundRadius(UIUtil.dip2px(context,3));
                indicator.setStartInterpolator(new AccelerateDecelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));
                return  indicator;
            }
        });
        magic.setNavigator(navigator);
        ViewPagerHelper.bind(magic,page);
    }




    public class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return ima.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(ima.get(position));
            return ima.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView(ima.get(position));
        }
    }
}
