package com.fengmi.he.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fengmi.he.R;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/20.
 */
/*
 *类的用途：注册功能
 *@name:贺晓雯
 *@date:{2017/4/20}
 */


public class ResgstActivity extends Activity {

    private EditText user;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resgst_layout);
        ImageButton back = (ImageButton) findViewById(R.id.ib_login_back);
        user = (EditText) findViewById(R.id.reg_user);
        pwd = (EditText) findViewById(R.id.reg_pwd);
        Button regst = (Button) findViewById(R.id.bt_regst);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResgstActivity.this,LoginActivity.class));
            }
        });
        regst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(TextUtils.isEmpty(user.getText().toString())&&TextUtils.isEmpty(pwd.getText().toString())){
                  Toast.makeText(ResgstActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                      return;
              }
                setData();
                
            }

         
        });


    }
    private void setData() {
        String url="http://169.254.217.5:8080/bullking1/register?name="+user.getText().toString()+"&pwd="+pwd.getText().toString()+"";
        OkHttpClient okhttpclient=new OkHttpClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call=okhttpclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String s=  response.body().string();
                Gson gson=new Gson();
                // gson.fromJson(s,);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ResgstActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject object=new JSONObject(s);
                            String dataStr = object.getString("dataStr");
                            if(dataStr.equals("register succeed")){
                                Toast.makeText(ResgstActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ResgstActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
        
    }
}
