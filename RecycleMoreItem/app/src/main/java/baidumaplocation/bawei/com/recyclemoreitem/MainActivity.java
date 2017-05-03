package baidumaplocation.bawei.com.recyclemoreitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.okhttp.Request;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String str = "http://i.dxy.cn/snsapi/home/feeds/list/all?sid=4df0360f-2a20-4198-beb8-4dc5660c4f08&u=zhetianyishou&s=10&mc=0000000049029dcaffffffff99d603a9&token=TGT-13165-buaw5fHpqLlefw9bSOB0oF41fobaV4rMZmK-50&hardName=iToolsAVM_T0008098S&ac=4124c5f1-2029-4fda-b06f-a87ac5ad8d11&bv=2013&vc=6.0.6&tid=c25e673d-e82a-4e46-bd4e-c1e86d497126&vs=4.4.4&ref_tid=54720e1a-7eed-4993-9f51-3d760f3d0b2e";
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
       getData();
    }

    private void getData() {
          OkHttpUtils.getAsyn(str, new OkHttpUtils.ResultCallback<infoBean>() {
              @Override
              public void onError(Request request, Exception e) {

              }

              @Override
              public void onResponse(infoBean response) {
                  List<infoBean.ItemsBean> items = response.getItems();
                  rv.setAdapter(new MyAdapter(MainActivity.this,items));

              }
          });
    }
}
