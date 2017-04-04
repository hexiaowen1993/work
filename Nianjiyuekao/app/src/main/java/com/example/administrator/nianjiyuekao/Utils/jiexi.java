package com.example.administrator.nianjiyuekao.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/4.
 */

public class jiexi {
    public static String jie(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] by = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(by)) != -1) {
                byteArrayOutputStream.write(by, 0, len);
            }
            return byteArrayOutputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
