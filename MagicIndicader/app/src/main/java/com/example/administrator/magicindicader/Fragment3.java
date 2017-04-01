package com.example.administrator.magicindicader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/1.
 */

public class Fragment3 extends Fragment {
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_URL = "key_url";
    private String title;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Bundle bundle = getArguments();
        title = bundle.getString(KEY_TITLE, "");
        url=bundle.getString(KEY_URL,"");*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_simple_title,container,false);
        TextView tv_title= (TextView) view.findViewById(R.id.a);
        //TextView tv_url= (TextView) view.findViewById(R.id.tv_url);
        tv_title.setText("体育");
        return view;
    }

    public String getTitle() {
        return title;
    }

}
