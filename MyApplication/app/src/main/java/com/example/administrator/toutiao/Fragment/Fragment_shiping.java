package com.example.administrator.toutiao.Fragment;

import android.os.Bundle;
import android.os.Handler;
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
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;

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
    private SpringView spring;
    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull,R.drawable.mt_pull01,R.drawable.mt_pull02,R.drawable.mt_pull03,R.drawable.mt_pull04,R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01,R.drawable.mt_refreshing02,R.drawable.mt_refreshing03,R.drawable.mt_refreshing04,R.drawable.mt_refreshing05,R.drawable.mt_refreshing06};
    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01,R.drawable.mt_loading02};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View v=inflater.inflate(R.layout.shiping_listview,null);
        lv = (ListView) v.findViewById(R.id.lv);
        spring= (SpringView) v.findViewById(R.id.spring_shi);
        spring.setType(SpringView.Type.FOLLOW);
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
        spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spring.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spring.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
        spring.setHeader(new MeituanHeader(getActivity(),pullAnimSrcs,refreshAnimSrcs));
        spring.setFooter(new MeituanFooter(getActivity(),loadingAnimSrcs));

    }
}
