package com.fengmi.he.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fengmi.he.Bean.CheBean;
import com.fengmi.he.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/20.
 */

public class CheAdapter extends BaseAdapter {
    private Context context;
    private List<CheBean.CartItemListBean> list;
    private Onclick trans;
    private Onclick isck;
    private int pos;
    private Handler handler = new Handler();

    public CheAdapter(Context context, List<CheBean.CartItemListBean> list) {
        this.context = context;
        this.list = list;
    }
//设置全选的box
    public float selectAll(){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(true);
        }
        notifyDataSetChanged();
        return totalP();
    }
//设置反选的逻辑
    public float selectNone(){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(false);
        }
        notifyDataSetChanged();
        return totalP();
    }
     //设置价格
    private float totalP(){
        float price = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                price += list.get(i).getPrice();
            }
        }
        int p = (int)price*100;
        return p/100f;
    }

    //接口回调
    //定义一个接口
    public interface Onclick {
        void onClick(float totalPrice, boolean flag);
    }

    //对外提供访问的方法
    public void setOnTransValues(Onclick trans) {
        this.trans = trans;
    }

    public void setCheckValues(Onclick tran) {
        this.isck = tran;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.che_buju, null);
            holder.box = (CheckBox) convertView.findViewById(R.id.che_box);
            holder.name = (TextView) convertView.findViewById(R.id.che_goods_name);
            holder.price = (TextView) convertView.findViewById(R.id.che_price);
            holder.pic = (ImageView) convertView.findViewById(R.id.che_pic);
            holder.bt = (Button) convertView.findViewById(R.id.che_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.box.setChecked(list.get(position).isCheck());
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice() + "");
        Glide.with(context).load(list.get(position).getPic()).into(holder.pic);
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = position;
                SharedPreferences sharedPreferences=context.getSharedPreferences("coin",MODE_PRIVATE);
               int uid = sharedPreferences.getInt("id", -1);
                netQuest(list.get(position).getProductID(),uid);
            }
        });
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //算价格
                boolean f = list.get(position).isCheck();
                list.get(position).setCheck(!f);
                //
                trans.onClick(totalP(), isSelect());
            }
        });
        return convertView;
    }

    class ViewHolder {
        CheckBox box;
        ImageView pic;
        TextView name;
        TextView price;
        Button bt;
    }

    private boolean isSelect(){
        boolean f = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck() == false) {
                f = false;
                break;
            }
        }
        return f;
    }

    private void netQuest(int id,int uid){
        String url = "http://169.254.217.5:8080/bullking1/deletepro?productID=" + id+"&userID="+uid;
        Toast.makeText(context, id+"", Toast.LENGTH_SHORT).show();
        OkHttpClient okhttpclient = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().get().build();
        Call call = okhttpclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String s = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject object = null;
                        try {
                            object = new JSONObject(s);
                            int count = object.getInt("count");
                            if (count == 1) {
                                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                                list.remove(pos);
                                trans.onClick(totalP(), isSelect());
                                notifyDataSetChanged();
                            }else {
                                Toast.makeText(context, count+"", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }
}
