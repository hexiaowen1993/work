package com.example.administrator.nianjiyuekao.Utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by Administrator on 2017/4/4.
 */

public class AppImageLoader {
    public static DisplayImageOptions image(int a){
        DisplayImageOptions options=new DisplayImageOptions
                .Builder()
                .showImageOnLoading(a)
                .showImageForEmptyUri(a)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        return options;
    }
}
