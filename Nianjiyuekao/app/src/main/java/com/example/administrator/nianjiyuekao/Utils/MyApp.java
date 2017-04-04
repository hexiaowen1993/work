package com.example.administrator.nianjiyuekao.Utils;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2017/4/4.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                . memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(10 * 1024 * 1024)
                .memoryCacheExtraOptions(480,800)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
