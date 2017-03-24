package com.example.administrator.toutiao.Util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.toutiao.Bean.GuanzhuBean;
import com.example.administrator.toutiao.Bean.TuijianBean;
import com.example.administrator.toutiao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class GuanzhuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GuanzhuBean> list;

    public GuanzhuAdapter(Context context, ArrayList<GuanzhuBean> list) {
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
       ViewHolder  holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.guanzhu,null);
            holder=new ViewHolder();
            holder.title= (TextView) convertView.findViewById(R.id.title_gguan);
            holder.url= (TextView) convertView.findViewById(R.id.url_guan);
            holder.pic= (ImageView) convertView.findViewById(R.id.pic_guan);
            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }
       holder.title.setText(list.get(position).getTitle());
        holder.url.setText(list.get(position).getUrl());
        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.pic);
        return convertView;
    }

    class ViewHolder{
        TextView title;
        TextView url;
        ImageView pic;

    }
}
