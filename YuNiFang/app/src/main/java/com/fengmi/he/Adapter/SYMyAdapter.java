package com.fengmi.he.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fengmi.he.Activity.Shopping;
import com.fengmi.he.Bean.ShouyeBean;
import com.fengmi.he.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/17.
 */

public class SYMyAdapter extends RecyclerView.Adapter<SYMyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> list;

    public SYMyAdapter(Context context, ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context)
               .inflate(R.layout.fenlei_gv_buju,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getGoods_img()).into(holder.img);
        holder.efficacy.setText(list.get(position).getEfficacy());
        holder.goods_name.setText(list.get(position).getGoods_name());
        holder.price.setText(list.get(position).getShop_price()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView efficacy;
        TextView goods_name;
        TextView price;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.goods_img);
            efficacy = (TextView) itemView.findViewById(R.id.efficacy);
            goods_name = (TextView) itemView.findViewById(R.id.goods_name);
            price = (TextView) itemView.findViewById(R.id.shop_price);
              itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(context, Shopping.class);
                      Intent id = intent.putExtra("id", list.get(getAdapterPosition()).getId());
                     context.startActivity(intent);
                  }
              });

        }
    }
}
