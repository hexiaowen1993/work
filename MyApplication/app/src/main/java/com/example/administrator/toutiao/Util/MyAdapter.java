package com.example.administrator.toutiao.Util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amiao.bitmapimagelibary.BitmapUtils;
import com.example.administrator.toutiao.Bean.TuijianBean;
import com.example.administrator.toutiao.C;
import com.example.administrator.toutiao.Chakan;
import com.example.administrator.toutiao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<TuijianBean> list;

    static final int Type2 = 0;
    static final int Type3 = 1;
    private BitmapUtils utils;

    public MyAdapter(Context context, List<TuijianBean> list) {
        this.context = context;
        this.list = list;
        utils = new BitmapUtils(context);
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getImgextra()!=null&&list.get(position).getImgsrc()==null) {
            return Type2;
        }
        if (list.get(position).getImgextra()!=null && list.get(position).getImgsrc()!=null) {
            return Type3;
        }
        return Type2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        int type = getItemViewType(position);
        holder = new viewHolder();
        if (convertView==null){
            switch (type){
                case Type2:
                    convertView = View.inflate(context,R.layout.shouye_buju2,null);
                    holder.textView= (TextView) convertView.findViewById(R.id.title2);
                    holder.textView1= (TextView) convertView.findViewById(R.id.media_name2);
                    holder.textView2= (TextView) convertView.findViewById(R.id.pinlun1);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.pic2);


                    break;
                case Type3:
                    convertView = View.inflate(context,R.layout.shouye_buju1,null);
                    holder.textView= (TextView) convertView.findViewById(R.id.title1);
                    holder.textView1= (TextView) convertView.findViewById(R.id.media_name1);
                    holder.textView2= (TextView) convertView.findViewById(R.id.pinlun1);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.buju1_p);
                    holder.imageView1 = (ImageView) convertView.findViewById(R.id.buju1_i);
                    holder.imageView2 = (ImageView) convertView.findViewById(R.id.buju1_c);

                    break;
            }

            convertView.setTag(holder);
        }else{
            holder = (viewHolder) convertView.getTag();
        }
        switch (type){
            case Type2:
                holder.textView.setText(list.get(position).getTitle());
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      Intent intent=new Intent(context,Chakan.class);
                        intent.putExtra("name",list.get(position).getUrl_3w());
                        context.startActivity(intent);

                    }
                });
                holder.textView1.setText(list.get(position).getSource());

                utils.disPlay(holder.imageView,list.get(position).getImgsrc());
               // x.image().bind(holder.imageView,list.get(position).getImgsrc());
                break;
            case Type3:
                holder.textView.setText(list.get(position).getTitle());
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context,Chakan.class);
                        intent.putExtra("name",list.get(position).getUrl_3w());
                        context.startActivity(intent);

                    }
                });
                holder.textView1.setText(list.get(position).getSource());
                holder.textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
             utils.disPlay(holder.imageView,list.get(position).getImgextra().get(0).getImgsrc());
               // x.image().bind(holder.imageView,list.get(position).getImgextra().get(0).getImgsrc());
                utils.disPlay(holder.imageView1,list.get(position).getImgextra().get(1).getImgsrc());
              //  x.image().bind(holder.imageView1,list.get(position).getImgextra().get(1).getImgsrc());
                utils.disPlay(holder.imageView2,list.get(position).getImgsrc());
              //  x.image().bind(holder.imageView2,list.get(position).getImgsrc());
                break;
        }

        return convertView;
    }
    class viewHolder{
        TextView textView;
        TextView textView1;
        TextView textView2;
        ImageView imageView;
        ImageView imageView1;
        ImageView imageView2;
    }
}