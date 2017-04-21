package com.fengmi.he.FragmentPakage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengmi.he.Activity.FenleiActivity;
import com.fengmi.he.Activity.Shopping;
import com.fengmi.he.Adapter.Fr_fenleiAdapter;
import com.fengmi.he.Bean.FenleiBean;
import com.fengmi.he.R;
import com.fengmi.he.Util.MyGriView;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Fragment_fenlei extends Fragment {

    private MyGriView gv;
    private List<FenleiBean.DataBean.GoodsBriefBean> goodsBrief;
    private ImageView bushui;
    private ImageView meibai;
    private ImageView kongyou;
    private ImageView shihuitaozhuang;
    private Button zhongxing;
    private Button mianmo;
    private ImageView mingan;
    private ImageView jiemianru;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fr_fenlei,null);
        gv = (MyGriView) v.findViewById(R.id.fenlei_gv);

       // "补水保湿", "美白提亮", " 控油祛痘", " 实惠套装", "中性肤质", "敏感肤质", " 贴士面膜",
        bushui = (ImageView) v.findViewById(R.id.bushuibaoshi);
        meibai = (ImageView)v.findViewById(R.id.meibai);
        kongyou = (ImageView)v.findViewById(R.id.kongyou);
        shihuitaozhuang = (ImageView)v.findViewById(R.id.shihuitaozhuang);
        zhongxing = (Button) v.findViewById(R.id.zhongxing);
       mianmo = (Button) v.findViewById(R.id.mingan);
       mingan = (ImageView)v.findViewById(R.id.mianmo);
        jiemianru = (ImageView) v.findViewById(R.id.jiemianru);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getServerdata();
        getDianji();
gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),Shopping.class);
        intent.putExtra("id",goodsBrief.get(position).getId());
        startActivity(intent);
    }
});
    }





    private void getDianji() {
        bushui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",0);
                startActivity(intent);
            }
        });
        meibai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",1);
                startActivity(intent);
            }
        });
        kongyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",2);
                startActivity(intent);
            }
        });
        shihuitaozhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",3);
                startActivity(intent);
            }
        });
        zhongxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",4);
                startActivity(intent);
            }
        });
        mianmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",5);
                startActivity(intent);
            }
        });
        mingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",6);
                startActivity(intent);
            }
        });
        jiemianru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FenleiActivity.class);
                intent.putExtra("name",7);
                startActivity(intent);
            }
        });
    }

    private void getServerdata() {
        String url="http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
        OkHttpClient okhttpclient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Call call=okhttpclient.newCall(request);
        call.enqueue(new Callback() {



            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                FenleiBean fenleiBean = gson.fromJson(string, FenleiBean.class);
                goodsBrief = fenleiBean.getData().getGoodsBrief();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Fr_fenleiAdapter adapter=new Fr_fenleiAdapter(getActivity(), goodsBrief);
                        gv.setAdapter(adapter);


                    }
                });


            }
        });
    }


}
