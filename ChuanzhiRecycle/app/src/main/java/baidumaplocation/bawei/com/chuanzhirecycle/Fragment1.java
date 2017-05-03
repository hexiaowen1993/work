package baidumaplocation.bawei.com.chuanzhirecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class Fragment1 extends Fragment {
    private RecyclerView fra_recy1;
    private List<Bean.ResultBean.BrandsBean> brands;
    private OnTrans trans;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment1,null);
        fra_recy1 = (RecyclerView) v.findViewById(R.id.fra_recy1);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
          fra_recy1.setLayoutManager(new LinearLayoutManager(getActivity()));
       //解析数据
        setServerData();
    }

    private void setServerData() {

         String url="http://www.babybuy100.com/API/getShopOverview.ashx";
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {


            /*    //下载功能
                InputStream inputStream = response.body().byteStream();
                FileOutputStream outputStream=new FileOutputStream(new File("/sdcard/xiaowen.json"));
                byte[] by= new byte[2048];
                  int len=0;
                while((len=inputStream.read(by))!=-1){
                     outputStream.write(by,0,len);
                }
                outputStream.flush();
*/



                final String string = response.body().string();
                Gson gson=new Gson();
                Bean bean = gson.fromJson(string, Bean.class);
                brands = bean.getResult().getBrands();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("aaaaa",string);
                        MyAdapter myAdapter=new MyAdapter();
                        fra_recy1.setAdapter(myAdapter);
                    }
                });
            }
        });

    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=View.inflate(getContext(),R.layout.frag_item1,null);
            MyViewHolder holder=new MyViewHolder(v);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
               holder.title.setText(brands.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return brands.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView title;

            public MyViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title);
                 itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         List<Bean.ResultBean.BrandsBean.ProductsBean> products = brands.get(getLayoutPosition()).getProducts();
                         trans.trans(products);
                     }
                 });

            }
        }
    }

//定义一个借口

    public interface  OnTrans{
    void  trans(List<Bean.ResultBean.BrandsBean.ProductsBean> list);
}
    //对外提供访问的方
    public void setOnTransValues(OnTrans trans){
        this.trans=trans;

    }

}
