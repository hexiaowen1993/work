package com.fengmi.he.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fengmi.he.Bean.CheBean;
import com.fengmi.he.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class CheAdapter extends BaseAdapter {
    private Context context;
    private List<CheBean.CartItemListBean> list;

    public CheAdapter(Context context, List<CheBean.CartItemListBean> list) {
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.che_buju, null);
            holder.name = (TextView) convertView.findViewById(R.id.che_goods_name);
            holder.price = (TextView) convertView.findViewById(R.id.che_price);
            holder.pic = (ImageView) convertView.findViewById(R.id.che_pic);
            holder.name= (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice() + "");
        Glide.with(context).load(list.get(position).getPic()).into(holder.pic);
        holder.number.setText(list.get(position).getId());
        return convertView;
    }

    class ViewHolder {
        ImageView pic;
        TextView name;
        TextView price;
        TextView number;

    }
}
