package com.example.administrator.toutiao.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.toutiao.Bean.TuijianBean;
import com.example.administrator.toutiao.R;
import com.example.administrator.toutiao.Util.MyAdapter;
import com.example.administrator.toutiao.Zhu;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xlistview.bawei.com.xlistviewlibrary.XListView;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Fragment_Tuijian extends Fragment {
    private String url;
    private List<TuijianBean> list = new ArrayList<>();

    public void setUrl(String url) {
        this.url = url;
    }

    private ListView xlv;

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
        RequestParams params = new RequestParams(url);
        Log.i("aaa", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Log.i("asd",result);
               /* Gson gson = new Gson();
                TuijianBean bean = gson.fromJson(result, TuijianBean.class);
                Log.i("asd",bean.toString());
                List<TuijianBean.T1348647909107Bean> list = bean.getT1348647909107();
                 Log.i("addddddddddddddd",list.size()+"");
                Toast.makeText(getActivity(), "das"+list.size(), Toast.LENGTH_SHORT).show();
                Zhu zhu = (Zhu) getActivity();
                MyAdapter adapter = new MyAdapter(zhu, list);
            xlv.setAdapter(adapter);*/

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
