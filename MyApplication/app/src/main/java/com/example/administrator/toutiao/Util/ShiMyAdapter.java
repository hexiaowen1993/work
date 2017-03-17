package com.example.administrator.toutiao.Util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.administrator.toutiao.Bean.ShipingBean;
import com.example.administrator.toutiao.R;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;



/**
 * Created by Administrator on 2017/3/17.
 */

public class ShiMyAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<ShipingBean> list;

    public ShiMyAdapter(Context context, ArrayList<ShipingBean> list) {
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
            convertView=View.inflate(context, R.layout.shi_buju,null);
            holder=new ViewHolder();
            holder.jc= (JCVideoPlayerStandard) convertView.findViewById(R.id.jcvideo);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        boolean setUp = holder.jc.setUp(list.get(position).getMp4_url(),list.get(position).getTitle());
        if (setUp) {
            Glide.with(context).load(list.get(position).getCover()).into(holder.jc.thumbImageView);
        }
        return convertView;
    }
    class ViewHolder{
        JCVideoPlayerStandard jc;
    }
}
