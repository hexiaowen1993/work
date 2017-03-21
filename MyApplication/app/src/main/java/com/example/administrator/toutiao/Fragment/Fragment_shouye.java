package com.example.administrator.toutiao.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.administrator.toutiao.Bean.Shouye_Bean;
import com.example.administrator.toutiao.Pindao;
import com.example.administrator.toutiao.R;
import com.example.administrator.toutiao.Zhu;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public class Fragment_shouye extends Fragment {
    private String[] tt = {"电影", "消息", "教育", "社会", "娱乐", "科技", "汽车", "体育", "财经","彩票"};
   private String[] aa={"a","b","c","d","e","f","g","h","i","g"};
    String[] url1 = {
            "T1348648650048",//电影
            "T1371543208049",//消息
            "T1348654225495",// 教育
            "T1348648037603",// 社会
            "T1348648517839",//娱乐
            "T1348649580692",// 科技
            "T1348654060988",// 汽车
            "T1348649079062",// 体育
            "T1348648756099",// 财经
            "T1348648141035"// 彩票
    };
    private List<Shouye_Bean.DataBeanX.DataBean> data;
    private TabLayout tab;
    private ViewPager page;
    ArrayList<Fragment> fr;
    private ImageButton pin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_shouye, null);
        tab = (TabLayout) v.findViewById(R.id.tab);
        page = (ViewPager) v.findViewById(R.id.shouye_page);
        pin = (ImageButton) v.findViewById(R.id.pin);
        return v;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
           pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zhu zhu = (Zhu) getActivity();
                startActivity(new Intent(zhu, Pindao.class));
            }
        });
        fr = new ArrayList<Fragment>();
        for (int i = 0; i <aa.length; i++) {
            Fragment_Tuijian tui = new Fragment_Tuijian();
           /* String a = "http://c.m.163.com/nc/article/headline/" + url1[i] + "/0-20.html";
            tui.setUrl(a);*/

            fr.add(tui.newInstance(aa[i]));
        }
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        MyAdapter adapter = new MyAdapter(getChildFragmentManager(), fr);
        tab.setupWithViewPager(page);
        tab.setTabsFromPagerAdapter(adapter);
        page.setAdapter(adapter);
      page.setOffscreenPageLimit(3);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fr;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> fr) {
            super(fm);
            this.fr = fr;

        }

        @Override
        public Fragment getItem(int position) {
            return fr.get(position);
        }

        @Override
        public int getCount() {
            return fr.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tt[position];
        }
    }
}
