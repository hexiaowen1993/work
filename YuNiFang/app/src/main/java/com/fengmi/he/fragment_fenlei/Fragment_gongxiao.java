package com.fengmi.he.fragment_fenlei;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fengmi.he.Activity.Shopping;
import com.fengmi.he.Adapter.Fr_fenleiAdapter;
import com.fengmi.he.Adapter.GXMyAdapter;
import com.fengmi.he.Bean.GXBean;
import com.fengmi.he.R;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Fragment_gongxiao extends Fragment {
private String url;
    private List<GXBean.DataBean> list;
    public void setUrl(String url) {
        this.url = url;
    }

    private GridView gv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate( R.layout.fr_gongxiao_gridview,null);
        gv = (GridView) v.findViewById(R.id.gongxiao_gv);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getServerData();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),Shopping.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void getServerData() {
        OkHttpClient okhttpclient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Call call=okhttpclient.newCall(request);
        call.enqueue(new Callback() {



            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();
                Gson gson=new Gson();
                GXBean gxBean = gson.fromJson(s, GXBean.class);
                list = (List<GXBean.DataBean>) gxBean.getData();
                Log.d("sssssssssssss", list.toString());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      GXMyAdapter adapter=new GXMyAdapter(getActivity(), list);
                        gv.setAdapter(adapter);

                    }
                });
            }
        });


    }
}
