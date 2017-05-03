package com.fengmi.he.FragmentPakage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.fengmi.he.Adapter.CheAdapter;
import com.fengmi.he.Bean.CheBean;
import com.fengmi.he.R;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Fragment_che extends Fragment {
    private CheAdapter adapter;
    private ListView lv;
    private String dataStr1;
    private int uid;
    private CheckBox box;
    public static TextView price1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_che, null);
        lv = (ListView) v.findViewById(R.id.che_lv);
        box = (CheckBox) v.findViewById(R.id.quanxuan);
        price1 = (TextView) v.findViewById(R.id.num_price);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("coin", MODE_PRIVATE);
        uid = sharedPreferences.getInt("id", -1);
        dataStr1 = sharedPreferences.getString("dataStr", "-2");
        //设置删除购物车
        setServerData();

        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box.isChecked()){
                    price1.setText(adapter.selectAll()+"");
                }else{
                    price1.setText(adapter.selectNone()+"");
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setServerData();
        }
    }

    private void setServerData() {
        String url = "http://169.254.217.5:8080/bullking1/cart?userID=" + uid;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        CheBean cheBean = gson.fromJson(string, CheBean.class);
                        List<CheBean.CartItemListBean> cartItemList = cheBean.getCartItemList();
                        adapter = new CheAdapter(getActivity(), cartItemList);
                        adapter.setOnTransValues(new CheAdapter.Onclick() {
                            @Override
                            public void onClick(float totalPrice, boolean flag) {
                                price1.setText(totalPrice+"");
                                box.setChecked(flag);
                            }
                        });
                        lv.setAdapter(adapter);

                    }
                });
            }
        });
    }
}