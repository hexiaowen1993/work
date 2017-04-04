package com.example.administrator.nianjiyuekao.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */

public class Bean {

    public DataBean data;
    public class DataBean{
        public ArrayList<NewsInfo> zhuanList;
        public class  NewsInfo{
            public String courseName;
            public String onlineTime;
            public String teacherAvatar;
            public String teacherName;

            @Override
            public String toString() {
                return "NewsInfo{" +
                        "courseName='" + courseName + '\'' +
                        ", onlineTime='" + onlineTime + '\'' +
                        ", teacherAvatar='" + teacherAvatar + '\'' +
                        ", teacherName='" + teacherName + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "zhuanList=" + zhuanList +
                    '}';
        }
    }



}
