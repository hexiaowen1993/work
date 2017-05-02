package com.example.administrator.yuanyuekao;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class RecycleV extends Activity {
    private ArrayList<Bean.ResultBean.RowsBean.InfoBean> infolist;
    private XRecyclerView recy;
   int num=1;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.xrecycle);
        recy = (XRecyclerView) findViewById(R.id.recyv);

        //解析数据
        setserverData();




        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setLoadingMoreEnabled(true);
        recy.setPullRefreshEnabled(true);
        recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                num=1;
                setserverData();
                recy.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                   recy.refreshComplete();

                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
               recy.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       num++;
                       setserverData();
                       recy.loadMoreComplete();
                   }
               },2000);
            }
        });
        setserverData();
    }

    private void setserverData() {
        String url="http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=2016041"+num+"091603";
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                final Bean bean = gson.fromJson(string, Bean.class);
             List<Bean.ResultBean.RowsBean> list= bean.getResult().getRows();
                infolist = new ArrayList<Bean.ResultBean.RowsBean.InfoBean>();
                for (int i = 0; i <list.size() ; i++) {
                    Bean.ResultBean.RowsBean.InfoBean info = list.get(i).getInfo();
                    infolist.add(info);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        setMyAdapter();
                    }


                });

            }
        });


    }
    private void setMyAdapter() {
        if(myAdapter==null){
            myAdapter = new MyAdapter();
            recy.setAdapter(myAdapter);
        }else{
            myAdapter.notifyDataSetChanged();
        }

    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(RecycleV.this).inflate(R.layout.item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
              holder.title.setText(infolist.get(position).getAddress());
            Glide.with(RecycleV.this).load(infolist.get(position).getDefault_image()).into(holder.pic);

        }

        @Override
        public int getItemCount() {
            return infolist.size();
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

}
