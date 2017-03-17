package com.example.administrator.toutiao.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.toutiao.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Fragment_yangguang extends Fragment {

    private TabLayout tab;
    private ViewPager page;
    private ArrayList<Fragment> list;
    private String[] title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate( R.layout.shiping,null);
        page = (ViewPager) v.findViewById(R.id.shiping_page);
        tab = (TabLayout) v.findViewById(R.id.shiping_tab);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = new String[]{
                "热点","娱乐","搞笑","精品"
        };
        String[] s=new String[]{
                "V9LG4B3A0",
                "V9LG4CHOR",
                "V9LG4E6VR",
                "00850FRB"};
        list = new ArrayList<Fragment>();
        for (int i = 0; i < title.length; i++) {
            Fragment_shiping shiping=new Fragment_shiping();
            shiping.setUrl("http://c.3g.163.com/nc/video/list/"+ s[i]+"/n/10-10.html");
            list.add(shiping);
        }
        MyAdapter adapter=new   MyAdapter(getFragmentManager());
        page.setAdapter(adapter);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setupWithViewPager(page);
        tab.setTabsFromPagerAdapter(adapter);


    }
public class MyAdapter extends FragmentPagerAdapter{

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}



}
