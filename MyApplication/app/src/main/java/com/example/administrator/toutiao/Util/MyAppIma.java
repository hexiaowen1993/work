package com.example.administrator.toutiao.Util;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyAppIma extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480,800).diskCacheFileNameGenerator(new Md5FileNameGenerator()).build();
        ImageLoader.getInstance().init(configuration);

        x.Ext.init(this);
        x.Ext.setDebug(false);

    }


}
