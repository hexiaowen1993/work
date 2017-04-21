package com.fengmi.he.Activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fengmi.he.Bean.ShopBean;
import com.fengmi.he.R;
import com.fengmi.he.Util.ImageBanner;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class Shopping extends Activity {
    private ShopBean shopBean;
    private String url;
    private Banner banner;
    private TextView shop_goods_name;
    private TextView shop_price;
    private ImageView shop_shoucang;
    private TextView shop_yunfei;
    private TextView shop_xiaoliang;
    private Button che;
    private Button buy;
    private String id1;
    private String dataStr;
    private String id;
    private int uid;
    private String dataStr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);
        findViewById(R.id.shop_shou);
        banner = (Banner) findViewById(R.id.shop_banner);
        shop_goods_name = (TextView) findViewById(R.id.shop_goods_name);
        shop_price = (TextView) findViewById(R.id.shop_price);
        shop_shoucang = (ImageView) findViewById(R.id.shop_shoucang);
        shop_yunfei = (TextView) findViewById(R.id.shop_yunfei);
        shop_xiaoliang = (TextView) findViewById(R.id.shop_xiaoliang);
        che = (Button) findViewById(R.id.shop_che);
        buy = (Button) findViewById(R.id.shop_buy);
        banner.setImageLoader(new ImageBanner());

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        url = "http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id=" + id;
        getServerData();
        getChe();
        getBuy();
        SharedPreferences sharedPreferences=getSharedPreferences("coin",MODE_PRIVATE);
        uid = sharedPreferences.getInt("id", -1);
        dataStr1 = sharedPreferences.getString("dataStr", "-2");



    }

    private void getBuy() {
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View pop = View.inflate(Shopping.this, R.layout.pop_buy, null);
                View part = View.inflate(Shopping.this, R.layout.shopping, null);
                final PopupWindow popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT, 430);
                ImageView pic = (ImageView) pop.findViewById(R.id.pop__buy_pic);
                TextView price = (TextView) pop.findViewById(R.id.pop_buy_price);
                Button queding = (Button) pop.findViewById(R.id.pop_buy_queding);
                TextView quxiao = (TextView) pop.findViewById(R.id.pop_buy_quxiao);
                TextView yunfei = (TextView) pop.findViewById(R.id.pop_buy_yunfei);
                popupWindow.setFocusable(true);
                Glide.with(Shopping.this).load(shopBean.getData().getGoods().getGoods_img()).into(pic);
                price.setText("￥" + shopBean.getData().getGoods().getShop_price());
                yunfei.setText("运费 ￥" + shopBean.getData().getGoods().getShipping_fee());
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAtLocation(part, Gravity.BOTTOM, 0, 0);
                quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                queding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        });

    }

    private void getChe() {

        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View pop = View.inflate(Shopping.this, R.layout.pop_add, null);
                View part = View.inflate(Shopping.this, R.layout.shopping, null);
                final PopupWindow popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT, 430);
                ImageView pic = (ImageView) pop.findViewById(R.id.pop_pic);
                TextView price = (TextView) pop.findViewById(R.id.pop_price);
                Button queding = (Button) pop.findViewById(R.id.pop_add_queding);
                TextView quxiao = (TextView) pop.findViewById(R.id.pop_quxiao);
                TextView yunfei = (TextView) pop.findViewById(R.id.pop_yunfei);
                popupWindow.setFocusable(true);
                Glide.with(Shopping.this).load(shopBean.getData().getGoods().getGoods_img()).into(pic);
                price.setText("￥" + shopBean.getData().getGoods().getShop_price());
                yunfei.setText("运费 ￥" + shopBean.getData().getGoods().getShipping_fee());
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAtLocation(part, Gravity.BOTTOM, 0, 0);
                quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                queding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://169.254.217.5:8080/bullking1/cart";
                        OkHttpClient okHttpClient = new OkHttpClient();
                        FormEncodingBuilder mfeb = new FormEncodingBuilder();
                        mfeb.add("productID",id);
                        mfeb.add("count", "10");
                        double price=  shopBean.getData().getGoods().getShop_price();
                        mfeb.add("price",price+"");
                        mfeb.add("name",shopBean.getData().getGoods().getGoods_name());
                        mfeb.add("pic", shopBean.getData().getGoods().getGoods_img());
                        mfeb.add("userID",uid+"");
                        Request request = new Request.Builder().url(url).post(mfeb.build()).build();
                        Call call = okHttpClient.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(Response response) throws IOException {
                                final String string = response.body().string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(dataStr1.equals("login succeed")){
                                            Toast.makeText(Shopping.this, "添加成功" + string, Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(Shopping.this, "请登录", Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                });


                            }
                        });

                        popupWindow.dismiss();
                    }

                });


            }
        });

    }

    private void getServerData() {
        OkHttpClient okhttpclint = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okhttpclint.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                shopBean = gson.fromJson(s, ShopBean.class);
                final List<ShopBean.DataBean.GoodsBean.GalleryBean> gallery = shopBean.getData().getGoods().getGallery();
                final ArrayList<String> bannerlist = new ArrayList<String>();
                for (int i = 0; i < gallery.size(); i++) {
                    String pic = gallery.get(i).getNormal_url();
                    bannerlist.add(pic);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(bannerlist);
                        banner.start();
                        shop_goods_name.setText(shopBean.getData().getGoods().getGoods_name());
                        shop_price.setText("￥" + shopBean.getData().getGoods().getShop_price());
                        shop_yunfei.setText("运费 ￥" + shopBean.getData().getGoods().getShipping_fee());
                        shop_xiaoliang.setText("销量 " + shopBean.getData().getGoods().getStock_number());

                    }
                });

            }
        });
    }


}
