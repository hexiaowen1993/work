package com.example.administrator.magicindicader;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.trs.channellib.channel.channel.helper.ChannelDataHelepr;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChannelDataHelepr.ChannelDataRefreshListenter {

    TitleFragmentAdapter adapter;
    List<Bean> myChannels;
    ChannelDataHelepr<Bean> dataHelepr;
    private int needShowPosition = -1;
    // private ArrayList<Bean> list;

    private ViewPager page;
    private ArrayList<Bean> title;
    private ArrayList<Bean> ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        page = (ViewPager) findViewById(R.id.page);
        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        ImageView switch_view = (ImageView) findViewById(R.id.iv_subscibe);

        //实例化ChannelDataHelepr对象  第一个参数是上下文，第二个参数是响应的频道管理器的监听，第三个是
        //要将管理器显示在哪个控件下方
        dataHelepr = new ChannelDataHelepr(this, this, findViewById(R.id._layout));

        //导航管理
        //CommonNavigator navigator = new CommonNavigator(MainActivity.this);
     //   navigator.setScrollPivotX(0.65f);
        ima = new ArrayList<Bean>();
        Fra f1 = new Fra();
        Fragment1 f2 = new Fragment1();
        Fragment2 f3 = new Fragment2();
        Fragment3 f4 = new Fragment3();
        Fragment4 f5 = new Fragment4();
        Bean be = new Bean("新闻", 0, 1, 0, f1);
        ima.add(be);
        ima.add(new Bean("热点", 0, 1, 0, f2));
        ima.add(new Bean("美食", 0, 0, 0, f3));
        ima.add(new Bean("体育", 1, 0, 0, f4));
        ima.add(new Bean("美女", 1, 0, 0, f5));
        dataHelepr.setSwitchView(switch_view);//触发频道管理的控件
        loadData();
        myChannels = new ArrayList<>();
        adapter = new TitleFragmentAdapter(getSupportFragmentManager(), myChannels);

        page.setAdapter(adapter);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setupWithViewPager(page);
       // tab.setTabsFromPagerAdapter(adapter);
    }
    // 实现方法  是刷新数据的方法  只有在频道发生变化的时候才会触发
    @Override
    public void updateData() {
        loadData();
    }

    //实现的方法  点击频道中的item的时候触发  可以根据是否有更新进行选择  viewpager切换的时机
    @Override
    public void onChannelSeleted(boolean update, final int posisiton) {
        if (!update) {
            //如果没有更新  那么viewpager显示的是posisiton位置的数据
            page.setCurrentItem(posisiton);
        } else {
            //如果更新数据了  那么默认显示的位置为posisiton，用needShowPosition接出来
            needShowPosition = posisiton;
        }

    }


    //每次数据加载完成后，过滤一遍，只显示订阅的频道
    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // String data = getFromRaw();
                //解析得到数据
                //  List<MyChannel> alldata = GsonUtil.jsonToBeanList(data, MyChannel.class);
                //Library中已经封装了数据库操作，获取需要显示的数据只需要一行代码即可
                //过滤数据  如果有新的频道会自动订阅并保存到数据库中  从数据库中得到数据
                final List<Bean> showChannels = dataHelepr.getShowChannels(ima);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //清空频道集合
                        myChannels.clear();
                        //添加从数据库中查询到的最新的集合数据
                        myChannels.addAll(showChannels);
                        //适配器刷新
                        adapter.notifyDataSetChanged();
                        //needShowPosition需要显示的默认位置-1
                        if (needShowPosition != -1) {
                            //如果不等于-1  viewpager显示当前位置
                            page.setCurrentItem(needShowPosition);
                            //显示完之后重新等于默认值，等待下一次被赋值
                            needShowPosition = -1;
                        }
                    }
                });

            }
        }).start();

    }
}
