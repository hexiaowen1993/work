package com.example.administrator.volleylianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xlistview.bawei.com.xlistviewlibrary.XListView;

public class MainActivity extends AppCompatActivity {
    int startNum=0;
    private XListView xlv;
    private JSONArray jsonArray;
    private MyAdapter adapter;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlv = (XListView) findViewById(R.id.xlv);
        checkBox = (CheckBox) findViewById(R.id.check);
         setxlvdata();
        getDate();

    }



    private void setxlvdata() {
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);
      xlv.setXListViewListener(new XListView.IXListViewListener() {
          @Override
          public void onRefresh() {
              checkBox.setChecked(false);
              startNum=0;
              getDate();

              xlv.postDelayed(new Runnable() {
                  @Override
                  public void run() {


                      xlv.stopRefresh();
                  }
              },2000);
          }

          @Override
          public void onLoadMore() {
               xlv.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       checkBox.setChecked(false);
                       startNum++;
                       getDate();
                       xlv.stopLoadMore();
                   }
               },2000);
          }
      });
          getDate();

    }
    private void getDate() {
        String url = "http://www.93.gov.cn/93app/data.do?" + "channelId=" + 0 + "&startNum=" + startNum;
        //设置请求队列
        Log.d("sssssssssssssssssssss",url);
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonobject=new JSONObject(s);
                    JSONArray data=jsonobject.getJSONArray("data");
                    if(startNum==0){
                        jsonArray = new JSONArray();
                    }
                    //把数据添加到jsonArray中去
                    for (int i = 0; i < data.length(); i++) {
                        jsonArray.put(data.get(i));
                    }
                      initListView(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        //把请求方式添加到队列
        queue.add(request);


    }
    private void initListView(JSONArray jsonArray) {

        if(adapter==null){
            adapter = new MyAdapter(this, jsonArray, new MyAdapter.OnCheckLitener() {
                @Override
                public void onCheck(Boolean check) {
                    checkBox.setChecked(check);
                }
            });
            adapter.setData(jsonArray);
            xlv.setAdapter(adapter);
        }else{
            adapter.setData(jsonArray);
        }

    }

    public  void oncheck(View view){
        switch (view.getId()){
            case  R.id.check:
                boolean falg=((CheckBox)view).isChecked();
                if(falg){
                    adapter.niticheck(falg);
                }else{
                    adapter.niticheck(falg);
                }
                break;
        }
    }

}
