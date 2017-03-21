package com.example.administrator.toutiao.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.toutiao.Bean.TuijianBean;
import com.example.administrator.toutiao.R;
import com.example.administrator.toutiao.Util.MyAdapter;
import com.example.administrator.toutiao.Zhu;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Fragment_Tuijian extends Fragment {

    private List<TuijianBean> list = new ArrayList<>();

    private ListView xlv;
    String[] url1 = {
            "T1348648650048",//电影
            "T1371543208049",//消息
            "T1348654225495",// 教育
            "T1348648037603",// 社会
            "T1348648517839",//娱乐
            "T1348649580692",// 科技
            "T1348654060988",// 汽车
            "T1348649079062",// 体育
            "T1348648756099",// 财经
            "T1348648141035"// 彩票
    };




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shouye_buju_tuijian, null);
        xlv = (ListView) v.findViewById(R.id.xlv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bu=getArguments();
        if(bu!=null){
            String name=bu.get("name").toString();
            if(name.equals("a")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[0] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("b")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[1] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("c")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[2] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("d")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[3] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("e")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[4] + "/0-20.html";
            }
            if(name.equals("f")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[5] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("g")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[6] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("h")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[7] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("i")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[8] + "/0-20.html";
                getServerData(a);
            }
            if(name.equals("g")){
                String a = "http://c.m.163.com/nc/article/headline/" + url1[9] + "/0-20.html";
                getServerData(a);
            }
        }
        }






    //Bundle传值
    public static Fragment_Tuijian newInstance(String name){
       Bundle bundle=new Bundle();
        bundle.putString("name",name);
        Fragment_Tuijian tuijian=new Fragment_Tuijian();
        tuijian.setArguments(bundle);
        return  tuijian;
    }


    public void getServerData(String url) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONArray jsonArray = jsonObject.optJSONArray(next);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.optJSONObject(i);
                        TuijianBean titleBean = gson.fromJson(object.toString(), TuijianBean.class);
                        list.add(titleBean);
                        Zhu zhu = (Zhu) getActivity();
                        MyAdapter adapter = new MyAdapter(zhu, list);
                        xlv.setAdapter(adapter);

                    }
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
