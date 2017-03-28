package com.example.administrator.toutiao.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/3/27.
 */

public class Internet  {
    public static boolean isNetWorkAvilable(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null){
            return  true;
        }
        return  false;
    }

    public static boolean iswifi(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&info.getType()==ConnectivityManager.TYPE_WIFI){
            return  true;
        }
        return  false;
    }

    public static boolean isMobile(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&info.getType()==manager.TYPE_MOBILE){
            return  true;
        }
        return false;
    }

}
