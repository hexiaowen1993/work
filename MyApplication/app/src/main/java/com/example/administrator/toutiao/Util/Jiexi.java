package com.example.administrator.toutiao.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Jiexi {
    public static String jie(InputStream inputStream){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] by=new byte[1024];
        int len=0;
        try {
            while((len=inputStream.read(by))!=-1){
            outputStream.write(by,0,len);
            }
            return outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
