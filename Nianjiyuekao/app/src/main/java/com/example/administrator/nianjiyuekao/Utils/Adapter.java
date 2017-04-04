package com.example.administrator.nianjiyuekao.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.nianjiyuekao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/4.
 */

public class Adapter  extends BaseAdapter{
    private Context context;
    private ArrayList<Bean.DataBean.NewsInfo> list;

    public Adapter(Context context, ArrayList<Bean.DataBean.NewsInfo> list) {
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
            convertView=View.inflate(context, R.layout.buju,null);
            holder=new ViewHolder();
            holder.courseName= (TextView) convertView.findViewById(R.id.courseName);
            holder.onlineTime= (TextView) convertView.findViewById(R.id.onlineTime);
            holder.teacherAvatar= (ImageView) convertView.findViewById(R.id.teacherAvatar);
            holder.teacherName= (TextView) convertView.findViewById(R.id.teacherName);
            holder.registerStudentNum= (TextView) convertView.findViewById(R.id.registerStudentNum);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.courseName.setText(list.get(position).courseName);
        holder.onlineTime.setText(list.get(position).onlineTime);
        ImageLoader.getInstance().displayImage(list.get(position).teacherAvatar,holder.teacherAvatar,AppImageLoader.image(R.mipmap.ic_launcher));

        return convertView;
    }
class ViewHolder{
        TextView courseName;
        TextView onlineTime;
        ImageView teacherAvatar;
        TextView teacherName;
        TextView registerStudentNum;

    }


}
