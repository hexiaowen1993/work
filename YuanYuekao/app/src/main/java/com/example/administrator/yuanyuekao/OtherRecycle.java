package com.example.administrator.yuanyuekao;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class OtherRecycle extends AppCompatActivity {

    private ByteArrayOutputStream bos;
    private boolean falg=true;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        falg=savedInstanceState.getBoolean("aa");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.otherrecycle);
        final RecyclerView recyclerView= (RecyclerView) findViewById(R.id.otherrecy);
        Button bt= (Button) findViewById(R.id.other_bt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String s = setServerData("data.json");
        //Toast.makeText(this, ""+s.toString(), Toast.LENGTH_SHORT).show();
            Gson gson = new Gson();
            ERBean erBean = gson.fromJson(s, ERBean.class);
            List<ERBean.ApkBean> apk = erBean.getApk();
            Log.i("1111",apk.toString());
            recyclerView.setAdapter(new My(this,apk));

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(falg) {
                   recyclerView.setLayoutManager(new GridLayoutManager(OtherRecycle.this, 2));
                   falg =false;
               }else{
                   recyclerView.setLayoutManager(new LinearLayoutManager(OtherRecycle.this));
                   falg=true;
               }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean("aa",falg);


    }


    public String setServerData(String str)  {
        InputStream stream = null;
        try {
            stream = getAssets().open(str);
            byte[]b = new byte[1024];
            int len;
            bos = new ByteArrayOutputStream();
            while ((len = stream.read(b))!=-1){
                bos.write(b,0,len);
            }
            return  bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
      return  str;

    }


}
