package com.example.administrator.magicindicader;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */

public class TitleFragmentAdapter extends FragmentPagerAdapter {
    List<Bean> channels;//关于频道对象的集合
    int id=1;
    Map<String,Integer> IdsMap=new HashMap<>();
    List<String> preIds=new ArrayList<>();
    int positions=0;
    public TitleFragmentAdapter(FragmentManager fm,@NonNull List<Bean> channels) {
        super(fm);
        this.channels=channels;
    }

    @Override
    public Fragment getItem(int position) {
        return channels.get(position).fragment;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("asasaaaaaaaaaaaa",channels.get(position).title);
        return channels.get(position).title;
    }

    @Override
    public long getItemId(int position) {
        positions=position;
        return IdsMap.get(getPageTitle(position));
    }
    //获得当前位置的条目
   @Override
    public int getItemPosition(Object object) {
        String title = channels.get(positions).title;
        int preId = preIds.indexOf(title);
        int newId=-1;
        int i=0;
        int size=getCount();
        for(;i<size;i++){
            if(getPageTitle(i).equals(title)){
                newId=i;
                break;
            }
        }
        if(newId!=-1&&newId==preId){
            return POSITION_UNCHANGED;
        }
        if(newId!=-1){
            return newId;
        }
        return POSITION_NONE;
    }
    //更新
   @Override
    public void notifyDataSetChanged() {
        for(Bean info:channels){
            if(!IdsMap.containsKey(info.title)){
                IdsMap.put(info.title,id++);
            }
        }
        super.notifyDataSetChanged();
        preIds.clear();
        int size=getCount();
        for(int i=0;i<size;i++){
            preIds.add((String) getPageTitle(i));
        }
    }
}
