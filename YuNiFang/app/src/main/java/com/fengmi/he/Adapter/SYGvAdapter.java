package com.fengmi.he.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fengmi.he.Bean.ShouyeBean;
import com.fengmi.he.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class SYGvAdapter extends BaseAdapter {
    private Context context;
    private List<ShouyeBean.DataBean.DefaultGoodsListBean> list;

    public SYGvAdapter(Context context, List<ShouyeBean.DataBean.DefaultGoodsListBean> list) {
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
        if(convertView==null){
            convertView=View.inflate(context, R.layout.fenlei_gv_buju,null);
            holder=new ViewHolder();
            holder.pic= (ImageView) convertView.findViewById(R.id.goods_img);
            holder.efficacy= (TextView) convertView.findViewById(R.id.efficacy);
            holder.goods_name= (TextView) convertView.findViewById(R.id.goods_name);
            holder.shop_price= (TextView) convertView.findViewById(R.id.shop_price);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getGoods_img()).into(holder.pic);
        holder.goods_name.setText(list.get(position).getGoods_name());
        holder.efficacy.setText(list.get(position).getEfficacy());
        holder.shop_price.setText("￥"+list.get(position).getShop_price());
        return convertView;
    }

    class ViewHolder{
        ImageView pic;
        TextView efficacy;
        TextView goods_name;
        TextView shop_price;

    }
}
