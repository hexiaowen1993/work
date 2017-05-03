package com.fengmi.he.FragmentPakage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fengmi.he.Activity.Shopping;
import com.fengmi.he.Adapter.SYGvAdapter;
import com.fengmi.he.Adapter.SYMyAdapter;
import com.fengmi.he.Bean.ShouyeBean;
import com.fengmi.he.R;
import com.fengmi.he.Util.ImageBanner;
import com.fengmi.he.Util.MyGriView;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Fragment_shouye extends Fragment {
    String url = "http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";

    private Banner banner;
    private List<ShouyeBean.DataBean.Ad1Bean> bannerlist;
    private RecyclerView rexiao;
    private RecyclerView youhui;
    private ImageView remen_pic;
    private RecyclerView remen;
    private List<ShouyeBean.DataBean.BestSellersBean.GoodsListBeanX> goodsList;
    private List<ShouyeBean.DataBean.BestSellersBean> rexiaolist;
    private ArrayList<String> bannerList;
    private ImageView shouye_binfenxinpin;
    private RecyclerView recycle_bingfeng;
    private ImageView shouye_remen_baokuan;
    private RecyclerView recycle_baokuan;
    private ImageView shouye_remen_mianmo;
    private RecyclerView recycle_mianmo;
    private ImageView shouye_remen_shihui;
    private RecyclerView recycle_shihui;
    private ImageView shouye_remen_mingxing;
    private RecyclerView recycle_mingxing;
    private ImageView shouye_remen_shuimian;
    private RecyclerView recycle_shuimian;
    private ImageView shouye_remen_nijiang;
    private RecyclerView recycle_nijiang;
    private ImageView shouye_remen_man;
    private RecyclerView recycle_man;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> subjectslist;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> nan;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> nijiang;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> shuimian;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> mingxing;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> shihui;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> mianmo;
    private ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean> baokuanlist;
    private MyGriView gv;
    private ArrayList<ShouyeBean.DataBean.DefaultGoodsListBean> goods;
    private Banner you;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_shouye, null);
        banner = (Banner) v.findViewById(R.id.sy_page);
           //扫描二维码
       ImageView erweima= (ImageView) v.findViewById(R.id.erweima);
        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 200);


            }
        });
        rexiao = (RecyclerView) v.findViewById(R.id.recycler_rexiao);
        youhui = (RecyclerView) v.findViewById(R.id.recycler_youhui);
        //
        remen_pic = (ImageView) v.findViewById(R.id.shouye_remen_pic);
        remen = (RecyclerView) v.findViewById(R.id.recycle_remen);
        //缤纷新品
        shouye_binfenxinpin = (ImageView) v.findViewById(R.id.shouye_binfenxinpin);
        recycle_bingfeng = (RecyclerView) v.findViewById(R.id.recycle_bingfeng);
        //爆款
        shouye_remen_baokuan = (ImageView) v.findViewById(R.id.shouye_remen_baokuan);
        recycle_baokuan = (RecyclerView) v.findViewById(R.id.recycle_baokuan);
        //面膜
        shouye_remen_mianmo = (ImageView) v.findViewById(R.id.shouye_remen_mianmo);
        recycle_mianmo = (RecyclerView) v.findViewById(R.id.recycle_mianmo);
        //实惠
        shouye_remen_shihui = (ImageView) v.findViewById(R.id.shouye_remen_shihui);
        recycle_shihui = (RecyclerView) v.findViewById(R.id.recycle_shihui);
        //明星
        shouye_remen_mingxing = (ImageView) v.findViewById(R.id.shouye_remen_mingxing);
        recycle_mingxing = (RecyclerView) v.findViewById(R.id.recycle_mingxing);
        //睡眠
        shouye_remen_shuimian = (ImageView) v.findViewById(R.id.shouye_remen_shuimian);
        recycle_shuimian = (RecyclerView) v.findViewById(R.id.recycle_shuimian);
        //泥浆
        shouye_remen_nijiang = (ImageView) v.findViewById(R.id.shouye_remen_nijiang);
        recycle_nijiang = (RecyclerView) v.findViewById(R.id.recycle_nijiang);
        //男人
        shouye_remen_man = (ImageView) v.findViewById(R.id.shouye_remen_man);
        recycle_man = (RecyclerView) v.findViewById(R.id.recycle_man);

        gv = (MyGriView) v.findViewById(R.id.shouye_gridv);
        you = (Banner) v.findViewById(R.id.banneryouhui);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setBannerData();
        rexiao.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_bingfeng.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_baokuan.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_mianmo.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_shihui.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_mingxing.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_shuimian.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_nijiang.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycle_man.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));


    }


    private void setBannerData() {
        banner.setImageLoader(new ImageBanner());
        you.setImageLoader(new ImageBanner());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            private ArrayList<String> dd;
            private ArrayList<ShouyeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> info;

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String string = response.body().string();
                Gson gson = new Gson();
                ShouyeBean shouyeBean = gson.fromJson(string, ShouyeBean.class);
                rexiaolist = shouyeBean.getData().getBestSellers();
                bannerlist = shouyeBean.getData().getAd1();
                bannerList = new ArrayList<String>();
                subjectslist = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(0).getGoodsList();
                baokuanlist = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(1).getGoodsList();
                mianmo = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(2).getGoodsList();
                shihui = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(3).getGoodsList();
                mingxing = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(4).getGoodsList();
                shuimian = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(5).getGoodsList();
                nijiang = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(6).getGoodsList();
                nan = (ArrayList<ShouyeBean.DataBean.SubjectsBean.GoodsListBean>) shouyeBean.getData().getSubjects().get(7).getGoodsList();
                goods = (ArrayList<ShouyeBean.DataBean.DefaultGoodsListBean>) shouyeBean.getData().getDefaultGoodsList();
                info = (ArrayList<ShouyeBean.DataBean.ActivityInfoBean.ActivityInfoListBean>) shouyeBean.getData().getActivityInfo().getActivityInfoList();
                dd = new ArrayList<String>();
                for (int i = 0; i < bannerlist.size(); i++) {
                    String image = bannerlist.get(i).getImage();

                    bannerList.add(image);
                }
                for (int i = 0; i < info.size(); i++) {
                    String ss = info.get(i).getActivityImg();
                    dd.add(ss);
                }
                for (int a = 0; a < rexiaolist.size(); a++) {
                    goodsList = rexiaolist.get(a).getGoodsList();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        banner.setImages(bannerList);
                        banner.start();
                        you.setImages(dd);

                        you.setBannerAnimation(Transformer.CubeOut);
                        you.setBannerStyle(BannerConfig.NOT_INDICATOR);
                        you.start();


                        rexiaodata();
                        xinpingdade();
                        baokuandate();
                        mianmodata();
                        shihuidata();
                        mingxingdata();
                        shuimiandata();
                        nijiangdata();
                        nandaata();
                        gvdata();


                    }


                });

            }
        });
    }


    private void gvdata() {
        SYGvAdapter adapter = new SYGvAdapter(getContext(), goods);
        gv.setAdapter(adapter);
    }

    //热门专题
    private void xinpingdade() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/170317101535113244907517202.jpg";
        Glide.with(getActivity()).load(url).into(shouye_binfenxinpin);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), subjectslist);
        recycle_bingfeng.setAdapter(adapter);
    }

    private void nandaata() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/1703171016528095841914776.jpg";
        Glide.with(getActivity()).load(url).into(shouye_remen_man);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), nan);
        recycle_man.setAdapter(adapter);
    }


    private void nijiangdata() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/170317101712315964509794047.jpg";
        Glide.with(getActivity()).load(url).into(shouye_remen_nijiang);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), nijiang);
        recycle_nijiang.setAdapter(adapter);
    }

    private void shuimiandata() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/170317101733912156132451214.jpg";
        Glide.with(getActivity()).load(url).into(shouye_remen_shuimian);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), shuimian);
        recycle_shuimian.setAdapter(adapter);
    }

    private void mingxingdata() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/17031710169411781500566632.jpg";
        Glide.with(getActivity()).load(url).into(shouye_remen_mingxing);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), mingxing);
        recycle_mingxing.setAdapter(adapter);

    }

    private void shihuidata() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/170317101653719081270367766.jpg";
        Glide.with(getActivity()).load(url).into(shouye_remen_shihui);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), shihui);
        recycle_shihui.setAdapter(adapter);

    }

    private void mianmodata() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/1703171051440237216708297.jpg";
        Glide.with(getActivity()).load(url).into(shouye_remen_mianmo);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), mianmo);
        recycle_mianmo.setAdapter(adapter);
    }

    private void baokuandate() {
        String url = "https://image.yunifang.com/yunifang/images/goods/temp/1703171015452295151972914.jpg";
        Glide.with(getActivity()).load(url).into(shouye_remen_baokuan);
        SYMyAdapter adapter = new SYMyAdapter(getActivity(), baokuanlist);
        recycle_baokuan.setAdapter(adapter);
    }

    //热销商品
    private void rexiaodata() {

        MyAdapter adapter = new MyAdapter();
        rexiao.setAdapter(adapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity())
                    .inflate(R.layout.fenlei_gv_buju, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Glide.with(getActivity()).load(goodsList.get(position).getGoods_img()).into(holder.img);
            holder.efficacy.setText(goodsList.get(position).getEfficacy());
            holder.goods_name.setText(goodsList.get(position).getGoods_name());
            holder.price.setText(goodsList.get(position).getShop_price() + "");

        }

        @Override
        public int getItemCount() {
            return goodsList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView img;
            TextView efficacy;
            TextView goods_name;
            TextView price;

            public MyViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.goods_img);
                efficacy = (TextView) itemView.findViewById(R.id.efficacy);
                goods_name = (TextView) itemView.findViewById(R.id.goods_name);
                price = (TextView) itemView.findViewById(R.id.shop_price);
                itemView.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), Shopping.class);
                        Intent id = intent.putExtra("id", goodsList.get(getAdapterPosition()).getId());
                        startActivity(intent);

                    }
                });

            }
        }
    }

}
