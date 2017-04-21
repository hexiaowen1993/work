package com.fengmi.he.FragmentPakage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fengmi.he.Activity.LoginActivity;
import com.fengmi.he.Adapter.Fr_meAdapter;
import com.fengmi.he.Bean.Fr_meBean;
import com.fengmi.he.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Fragment_Me extends Fragment {

    private ListView lv;
    private TextView name;
    private ImageView touxiang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_me, null);
        lv = (ListView) v.findViewById(R.id.me_lv);
        name = (TextView) v.findViewById(R.id.me_lv_name);
        touxiang = (ImageView) v.findViewById(R.id.me_touxiang);
         /* SharedPreferences sharedPreferences=getActivity().getSharedPreferences("coin",MODE_PRIVATE);
       int uid = sharedPreferences.getInt("id", -1);
        String ss=sharedPreferences.getString("user","");
        if(uid==26){
            name.setText(ss);
        }*/
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLVdata();
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });




    }

    private void setLVdata() {
        String[] name = {
                "我的订单", "我的现金券", "我的抽奖单", "我收藏的商品", "联系客服"
        };
        int[] pic = {
                R.mipmap.dingdan, R.mipmap.xianjin, R.mipmap.choujiangdan, R.mipmap.shoucang, R.mipmap.kefu
        };
        ArrayList<Fr_meBean> list = new ArrayList<Fr_meBean>();
        for (int i = 0; i < name.length; i++) {
            Fr_meBean be = new Fr_meBean(name[i], pic[i]);
            list.add(be);
        }
        Fr_meAdapter adapter = new Fr_meAdapter(getActivity(), list);
        lv.setAdapter(adapter);
    }
}
