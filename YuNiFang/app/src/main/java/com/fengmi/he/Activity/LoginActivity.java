package com.fengmi.he.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fengmi.he.R;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
/*
 *类的用途：登录功能
 *@name:贺晓雯
 *@date:{2017/4/20}
 */

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText pwd;
    private String dataStr;
    private int id;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ImageButton back = (ImageButton) findViewById(R.id.ib_login_back);
        TextView regist = (TextView) findViewById(R.id.tv_register);
        user = (EditText) findViewById(R.id.log_user);
        pwd = (EditText) findViewById(R.id.log_pwd);
        Button login = (Button) findViewById(R.id.log_login);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResgstActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(user.getText().toString()) && TextUtils.isEmpty(pwd.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                setData();
                startActivity(new Intent(LoginActivity.this, Zhu.class));
            }
        });
//保存数据
        SharedPreferences sharedPreferences=getSharedPreferences("coin",MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    private void setData() {
        OkHttpClient okHttpclient = new OkHttpClient();
        String url = "http://169.254.217.5:8080/bullking1/login";
        FormEncodingBuilder mFeb = new FormEncodingBuilder();
        mFeb.add("name", user.getText().toString());
        mFeb.add("pwd", pwd.getText().toString());
        final Request request = new Request.Builder().url(url).post(mFeb.build()).build();
        Call call = okHttpclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String s = response.body().string();
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        //{"dataStr":"login succeed","id":26}

                        try {
                            JSONObject object = new JSONObject(s);
                            id = object.getInt("id");
                            dataStr = object.getString("dataStr");
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            editor.putInt("id",id);
                            editor.putString("dataStr",dataStr);
                            editor.putString("user",user.getText().toString());
                            editor.commit();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });


    }
}
