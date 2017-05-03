package baidumaplocation.bawei.com.clearhuancunyuekao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Bean.DataBean> list;

    public MyAdapter(Context context, ArrayList<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context,R.layout.item,null);
        MyViewHolder holder1=new MyViewHolder(v);
        return holder1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.pic);
        holder.title.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            title = (TextView) itemView.findViewById(R.id.title);


        }
    }
}
