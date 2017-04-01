package com.example.administrator.magicindicader;

import android.support.v4.app.Fragment;

import com.trs.channellib.channel.channel.ChannelEntity;

/**
 * Created by Administrator on 2017/3/28.
 */

public class Bean  implements ChannelEntity.ChannelEntityCreater {
    //标题  频道类型  是否固定频道   url地址  是否订阅
    public String title;

    public int channelType;

    public int isFix;

    public int isSubscrible;
    public Fragment fragment;

    public Bean(String title, int channelType, int isFix, int isSubscrible, Fragment fragment) {
        this.title = title;
        this.channelType = channelType;
        this.isFix = isFix;
        this.isSubscrible = isSubscrible;
        this.fragment = fragment;
    }

    @Override
    public ChannelEntity createChannelEntity() {
        ChannelEntity entity=new ChannelEntity();
//是否是固定频道
        entity.setFixed(isFix==1);
        //显示的名称
        entity.setName(title);
        return entity;
    }
}
