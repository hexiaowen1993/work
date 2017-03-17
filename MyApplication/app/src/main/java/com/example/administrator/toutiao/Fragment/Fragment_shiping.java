package com.example.administrator.toutiao.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.toutiao.Bean.ShipingBean;
import com.example.administrator.toutiao.Bean.TuijianBean;
import com.example.administrator.toutiao.R;
import com.example.administrator.toutiao.Util.MyAdapter;
import com.example.administrator.toutiao.Util.ShiMyAdapter;
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

/**
 * Created by Administrator on 2017/3/17.
 */

public class Fragment_shiping extends Fragment {
    private String url;
    private ListView lv;
    private ArrayList<ShipingBean> list=new ArrayList<ShipingBean>();
    public void setUrl(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View v=inflater.inflate(R.layout.shiping_listview,null);
        lv = (ListView) v.findViewById(R.id.lv);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CacheCallback<String>() {
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
                        ShipingBean titleBean = gson.fromJson(object.toString(), ShipingBean.class);
                        list.add(titleBean);
                        Zhu zhu = (Zhu) getActivity();
                        ShiMyAdapter adapter=new ShiMyAdapter(getContext(),list);
                        lv.setAdapter(adapter);

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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }
}
