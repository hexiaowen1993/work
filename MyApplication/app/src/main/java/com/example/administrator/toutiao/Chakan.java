package com.example.administrator.toutiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2017/3/22.
 */

public class Chakan extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chakan);
        WebView web= (WebView) findViewById(R.id.web);
        Intent intent=getIntent();
        final String url = intent.getStringExtra("name");
        web.loadUrl(url);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        web.getSettings().setJavaScriptEnabled(true);//启用js
        web.getSettings().setBlockNetworkImage(false);//解决图片不显示

    }
}
