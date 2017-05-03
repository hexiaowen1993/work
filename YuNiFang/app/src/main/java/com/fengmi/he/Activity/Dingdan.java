package com.fengmi.he.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fengmi.he.Bean.CheBean;
import com.fengmi.he.Bean.OrderBean;
import com.fengmi.he.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/4/25.
 */

public class Dingdan extends Activity {


    private List<CheBean.CartItemListBean> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dingdan);
       ListView lv= (ListView) findViewById(R.id.dingdan_lv);
TextView priceT= (TextView) findViewById(R.id.ding_price);
Button dinfdan= (Button) findViewById(R.id.ding_tj);
        Intent intent=getIntent();
        //价格也要传过来
        String price = intent.getStringExtra("price");

         priceT.setText("共计："+price);
        //  list1=new ArrayList<CheBean.CartItemListBean>();
   list1 = (List<CheBean.CartItemListBean>) intent.getSerializableExtra("ding");
        //    list1.add(list);
        Log.d("sssssss",list1.toString());
        MyAdapter adapter=new MyAdapter();
       lv.setAdapter(adapter);
        //点击提交订单
      dinfdan.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
       String url="http://lexue365.51dangao.cn/api/order/add_order";
              AsyncHttpClient client=new AsyncHttpClient();
              //使用post请求，参数为：除了contact和mobile,remark/随意填写,其他的值为固定的
              //请求头：
              client.addHeader("userid",465+"");
              client.addHeader("cltid", "1");
              client.addHeader("token", "bbb6e99c4cd22930ea4c945d9932f98a");
              client.addHeader("mobile", "15718812708");
              RequestParams params = new RequestParams();
              // 请求体：
              params.put("activity_id",4);
              params.put("time_id",2927);
              params.put("child_num",1);
              params.put("contact","文");
              params.put("mobile","18401587588");
              params.put("remark",1);
               client.post(Dingdan.this, url, params, new TextHttpResponseHandler() {
                   @Override
                   public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                   }

                   @Override
                   public void onSuccess(int statusCode, Header[] headers, String responseString) {
                       //Toast.makeText(Dingdan.this, ""+responseString, Toast.LENGTH_SHORT).show();
                       String s=   responseString;
                       Gson gson = new Gson();
                       OrderBean orderBean = gson.fromJson(responseString, OrderBean.class);
                       Intent it = new Intent(getApplicationContext(), UpOrderActivity.class);
                       it.putExtra("orderBean",orderBean);
                       startActivity(it);


                   }
               }) ;

          }
      });

    }
    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list1.size();
        }

        @Override
        public Object getItem(int position) {
            return list1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(Dingdan.this, R.layout.che_buju, null);
                holder.che_goods_name = (TextView) convertView.findViewById(R.id.che_goods_name);
                holder.che_price = (TextView) convertView.findViewById(R.id.che_price);
                holder.pic = (ImageView) convertView.findViewById(R.id.che_pic);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.che_goods_name.setText(list1.get(position).getName());
            holder.che_price.setText(list1.get(position).getPrice() + "");
            Glide.with(Dingdan.this).load(list1.get(position).getPic()).into(holder.pic);

            return convertView;
        }
       class ViewHolder{
           ImageView pic;
           TextView che_goods_name;
           TextView che_price;

       }

    }

}
