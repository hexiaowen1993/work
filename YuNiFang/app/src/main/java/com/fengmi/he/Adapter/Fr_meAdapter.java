package com.fengmi.he.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengmi.he.Bean.Fr_meBean;
import com.fengmi.he.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Fr_meAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Fr_meBean> list;

    public Fr_meAdapter(Context context, ArrayList<Fr_meBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v=View.inflate(context, R.layout.me_lv_buju,null);
      ImageView pic= (ImageView) v.findViewById(R.id.me_lv_pic);
      TextView name= (TextView) v.findViewById(R.id.me_lv_name);
       pic.setImageResource(list.get(position).getPic());
        name.setText(list.get(position).getName());
        return v;
    }

}
