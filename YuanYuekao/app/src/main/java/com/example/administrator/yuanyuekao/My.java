package com.example.administrator.yuanyuekao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class My extends RecyclerView.Adapter<My.holder> {
    Context context;
    List<ERBean.ApkBean> apk;
    public My(Context context, List<ERBean.ApkBean> apk) {
        this.context =context;
        this.apk = apk;
    }

    @Override
    public My.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.other_item,null);
        holder h = new holder(inflate);
        return h;
    }

    @Override
    public void onBindViewHolder(My.holder holder, int position) {
       Glide.with(context).load(apk.get(position).getIconUrl()).placeholder(R.mipmap.ic_launcher).into(holder.pic);
        holder.tv.setText(apk.get(position).getName());
        String apkSize = apk.get(position).getApkSize();
        long lon = Long.parseLong(apkSize);
        int a= (int) (lon / 1024 / 1024);

        holder.aaa.setText(a+"M");
          //holder.aaa.setText(apk.get(position).getApkSize());
        String downloadTimes = apk.get(position).getDownloadTimes();
        long don = Long.parseLong(downloadTimes);
        double dd=don/10000;
        BigDecimal bg = new BigDecimal(dd);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        holder.down.setText(f1+"");
    }

    @Override
    public int getItemCount() {
        return apk.size();
    }
    class holder extends RecyclerView.ViewHolder {

         ImageView pic;
       TextView tv;
        TextView aaa;
        TextView down;

        public holder(final View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.other_pic);
            tv = (TextView) itemView.findViewById(R.id.other_tv);
            aaa = (TextView) itemView.findViewById(R.id.apk);
            down = (TextView) itemView.findViewById(R.id.down);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                      View pop=View.inflate(context,R.layout.items,null);
                     final PopupWindow popupwind=new PopupWindow(pop, 300,200);
                     popupwind.setFocusable(true);
                     popupwind.setOutsideTouchable(true);
                     ImageView pic= (ImageView) pop.findViewById(R.id.pic);

                     Glide.with(context).load(apk.get(getLayoutPosition()).getIconUrl()).into(pic);
                     View par=View.inflate(context,R.layout.otherrecycle,null);
                     popupwind.showAtLocation(itemView,Gravity.CENTER,0,0);
                     pic.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             popupwind.dismiss();
                         }
                     });
                     //Toast.makeText(context, ""+getLayoutPosition(), Toast.LENGTH_SHORT).show();

                 }
             });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("是否要删除");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            apk.remove(getLayoutPosition());
                            notifyDataSetChanged();
                            builder.create().dismiss();
                        }
                    });
                  builder.create().show();

                   /* apk.remove(getLayoutPosition());
                    notifyDataSetChanged();*/
                    return false;
                }
            });
        }
    }
}
