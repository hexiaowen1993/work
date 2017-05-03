package baidumaplocation.bawei.com.clearhuancunyuekao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dingwei= (Button) findViewById(R.id.dingwei);
        final TextView clean= (TextView) findViewById(R.id.clean);
        recyclerView = (RecyclerView) findViewById(R.id.recyv);
          recyclerView.setLayoutManager(new LinearLayoutManager(this));
          setServerData();


        String cacheSize = GlideCacheUtil.getInstance().getCacheSize(this);
        clean.setText(cacheSize);
          dingwei.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent();
                  intent.setData(Uri.parse("baidumap://map?"));
                  startActivity(intent);
              }
          });

        clean.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //消除所有缓存
             GlideCacheUtil.getInstance().clearImageAllCache(MainActivity.this);
                  //获取大小
                  String cacheSize = GlideCacheUtil.getInstance().getCacheSize(MainActivity.this);
                  clean.setText(cacheSize);
              }
          });


    }





    private void setServerData() {
        String url="http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().get().url(url).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                Bean bean = gson.fromJson(string, Bean.class);
                final ArrayList<Bean.DataBean> data = (ArrayList<Bean.DataBean>) bean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       MyAdapter adapter=new MyAdapter(MainActivity.this,data);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }


}
