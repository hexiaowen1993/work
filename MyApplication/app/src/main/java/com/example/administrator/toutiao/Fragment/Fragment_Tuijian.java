package com.example.administrator.toutiao.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.toutiao.Bean.TuijianBean;
import com.example.administrator.toutiao.Database.ShouyeDatabase;
import com.example.administrator.toutiao.R;
import com.example.administrator.toutiao.Util.Internet;
import com.example.administrator.toutiao.Util.MyAdapter;
import com.example.administrator.toutiao.Zhu;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.provider.UserDictionary.Words.APP_ID;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Fragment_Tuijian extends Fragment {
    private AlertDialog.Builder builder;
    private List<TuijianBean> list = new ArrayList<>();
    private PopupWindow pop;
    private ListView xlv;
    String[] url1 = {
            "T1348648650048",//电影
            "T1371543208049",//消息
            "T1348654225495",// 教育
            "T1348648037603",// 社会
            "T1348648517839",//娱乐
            "T1348649580692",// 科技
            "T1348654060988",// 汽车
            "T1348649079062",// 体育
            "T1348648756099",// 财经
            "T1348648141035"// 彩票
    };
    private Tencent mTencent;
    private SQLiteDatabase db;
    private View v11;
    private SpringView spring;
    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull,R.drawable.mt_pull01,R.drawable.mt_pull02,R.drawable.mt_pull03,R.drawable.mt_pull04,R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01,R.drawable.mt_refreshing02,R.drawable.mt_refreshing03,R.drawable.mt_refreshing04,R.drawable.mt_refreshing05,R.drawable.mt_refreshing06};
    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01,R.drawable.mt_loading02};
    private int indext=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v11 == null) {
            v11 = inflater.inflate(R.layout.shouye_buju_tuijian, null);
        }


        spring= (SpringView) v11.findViewById(R.id.spring);
        spring.setType(SpringView.Type.FOLLOW);
        xlv = (ListView) v11.findViewById(R.id.xlv);
        mTencent = Tencent.createInstance("1105602574", this.getActivity());
        ViewGroup    parent = (ViewGroup) v11.getParent();
        if (parent != null) {
            parent.removeView(v11);
        }
        return v11;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

             getData();


        spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {



                    @Override
                    public void run() {
                   //弹一个dailog，判断
                   boolean wifi= Internet.iswifi(getActivity());
                        if(!wifi){
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("是否要连接网络");

                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent in=null;
                                    if(android.os.Build.VERSION.SDK_INT>10){
                                        in=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                    }else{
                                        in=new Intent();
                                        in.setClassName("com.android.settings","com.android.settings.WirelessSettings");
                                    }
                                    startActivity(in);
                                    builder.create().dismiss();
                                }
                            });
                         builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 builder.create().dismiss();
                             }
                         });
                            builder.create().show();
                        }

                        getData();
                        spring.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //弹一个dailog，判断
                        boolean wifi= Internet.iswifi(getActivity());
                        if(!wifi){
                            builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("是否要连接网络");
                            builder.create().show();
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent in=null;
                                    if(android.os.Build.VERSION.SDK_INT>10){
                                        in=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                    }else{
                                        in=new Intent();
                                        in.setClassName("com.android.settings","com.android.settings.WirelessSettings");
                                    }
                                    startActivity(in);
                                    builder.create().dismiss();
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    builder.create().dismiss();
                                }
                            });

                        }


                        getData();
                        spring.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
        spring.setHeader(new MeituanHeader(getActivity(),pullAnimSrcs,refreshAnimSrcs));
        spring.setFooter(new MeituanFooter(getActivity(),loadingAnimSrcs));



        //点击关注放 把图片 ，title ，url 放到到数据库
        ShouyeDatabase cdb = new ShouyeDatabase(getActivity());
        db = cdb.getWritableDatabase();


        xlv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                View vv = View.inflate(getActivity(), R.layout.pop, null);
                ImageView guan = (ImageView) vv.findViewById(R.id.guan);
                ImageView fen = (ImageView) vv.findViewById(R.id.fen);
                pop = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, 200);
                pop.setTouchable(true);
                pop.setFocusable(true);
                pop.setOutsideTouchable(true);
                pop.setBackgroundDrawable(new ColorDrawable(0));
                view.getWindowToken();
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                guan.setOnClickListener(new View.OnClickListener() {

                    private String logname;

                    @Override
                    public void onClick(View v) {
                        //String sql = "insert into shouye(title,pic,webUrl) values('" + list.get(position).getTitle() + "','" + list.get(position).getImgsrc() + "','" + list.get(position).getUrl_3w() + "')";
                        //db.execSQL(sql);
                        String deng="select * from dengluxingxi ";
                        Cursor cursor=  db.rawQuery(deng,null);
                        while(cursor.moveToNext()){
                            logname = cursor.getString(1);
                            String pwd=cursor.getString(2);
                            indext++;
                        }
                       if(logname.equals("wen")){
                           String sql_sele = "insert into shouye(title,pic,webUrl) values('" + list.get(position).getTitle() + "','" + list.get(position).getImgsrc() + "','" + list.get(position).getUrl_3w() + "')";
                           db.execSQL(sql_sele);
                       } else{
                           Toast.makeText(getActivity(), "您还没有登录哦！", Toast.LENGTH_SHORT).show();
                       }

                        pop.dismiss();
                    }
                });
                fen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareListener myListener = new ShareListener();
                        final Bundle params = new Bundle();
                        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                        params.putString(QQShare.SHARE_TO_QQ_TITLE, list.get(position).getTitle());
                        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, list.get(position).getImgsrc());
                        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, list.get(position).getImgsrc());
                        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, list.get(position).getImgsrc());
                        mTencent.shareToQQ(getActivity(), params, myListener);

                        pop.dismiss();
                    }
                });
                return false;
            }
        });


    }

    private void getData() {

        Bundle bu = getArguments();
        if (bu != null) {
            String name = bu.get("name").toString();
            if (name.equals("a")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[0] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("b")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[1] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("c")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[2] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("d")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[3] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("e")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[4] + "/0-20.html";
            }
            if (name.equals("f")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[5] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("g")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[6] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("h")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[7] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("i")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[8] + "/0-20.html";
                getServerData(a);
            }
            if (name.equals("g")) {
                String a = "http://c.m.163.com/nc/article/headline/" + url1[9] + "/0-20.html";
                getServerData(a);
            }
        }


    }


    private class ShareListener implements IUiListener {

        @Override
        public void onCancel() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onComplete(Object arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onError(UiError arg0) {
            // TODO Auto-generated method stub

        }

    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ShareListener myListener = new ShareListener();
        Tencent.onActivityResultData(requestCode, resultCode, data, myListener);
    }

    //Bundle传值
    public static Fragment_Tuijian newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        Fragment_Tuijian tuijian = new Fragment_Tuijian();
        tuijian.setArguments(bundle);
        return tuijian;
    }


    public void getServerData(String url) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONArray jsonArray = jsonObject.optJSONArray(next);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.optJSONObject(i);
                        TuijianBean titleBean = gson.fromJson(object.toString(), TuijianBean.class);
                        list.add(titleBean);
                        Zhu zhu = (Zhu) getActivity();
                        MyAdapter adapter = new MyAdapter(zhu, list);
                        xlv.setAdapter(adapter);

                    }
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
