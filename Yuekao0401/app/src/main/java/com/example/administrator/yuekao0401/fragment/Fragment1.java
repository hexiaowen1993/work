package com.example.administrator.yuekao0401.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.yuekao0401.R;
import com.example.administrator.yuekao0401.Utils.Image;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/1.
 */

public class Fragment1 extends Fragment {

    private Banner banner;
    private String url1 = "https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg03.sogoucdn.com%2Fapp%2Fa%2F100520093%2F089cb779cbaf57c9-2aab3d69c9a5ee5c-bb826c276302cef8d7827529dd0cf76c.jpg&appid=122";
    private String url2 = "https://img01.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg04.sogoucdn.com%2Fapp%2Fa%2F100520093%2F2ad11b094c93197d-fc1961d77e6a39e9-265b4f7b99e31f7d5feac483c40b3208.jpg&appid=122";
    private String url3 = "http://pic1.sc.chinaz.com/Files/pic/pic9/201610/apic23618_s.jpg";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.xinwen, null);
        banner = (Banner) v.findViewById(R.id.viewpager);
        ListView lv = (ListView) v.findViewById(R.id.lv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        banner.setImageLoader(new Image());
        ArrayList<String> list = new ArrayList<String>();
        list.add(url1);
        list.add(url2);
        list.add(url3);
        banner.setImages(list);
        banner.start();

    }
}
