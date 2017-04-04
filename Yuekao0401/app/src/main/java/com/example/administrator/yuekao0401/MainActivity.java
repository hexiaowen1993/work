package com.example.administrator.yuekao0401;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.yuekao0401.fragment.Fragment1;
import com.example.administrator.yuekao0401.fragment.Fragment2;
import com.example.administrator.yuekao0401.fragment.Fragment3;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import cn.jpush.android.api.JPushInterface;

import static com.example.administrator.yuekao0401.fragment.Fragment2.mIUiListener;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private FrameLayout fra;
    private Fragment1 fr1;
    private Fragment2 fr2;
    private Fragment3 fr3;
    private RadioButton xinwen;
    private RadioButton wode;
    private RadioButton sanfang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        fra = (FrameLayout) findViewById(R.id.fra_layout);
        xinwen = (RadioButton) findViewById(R.id.xinwen);
        wode = (RadioButton) findViewById(R.id.wode);
        sanfang = (RadioButton) findViewById(R.id.sanfng);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fr1 = new Fragment1();
        fr2 = new Fragment2();
        fr3 = new Fragment3();

        transaction.add(R.id.fra_layout, fr1, "f1");
        transaction.add(R.id.fra_layout, fr2, "f2");
        transaction.add(R.id.fra_layout, fr3, "f3");
        setShowAndHidde(fr1, fr2, fr3);
        transaction.commit();
        xinwen.setOnClickListener(this);
        wode.setOnClickListener(this);
        sanfang.setOnClickListener(this);


    }

    private void setShowAndHidde(Fragment f1, Fragment f2, Fragment f3) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(f1);
        transaction.hide(f2);
        transaction.hide(f3);
        transaction.commit();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xinwen:

                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                setShowAndHidde(fr1, fr2, fr3);
                transaction1.commit();
                break;
            case R.id.sanfng:
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                setShowAndHidde(fr2, fr1, fr3);
                transaction2.commit();
                break;
            case R.id.wode:
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                setShowAndHidde(fr3, fr2, fr1);
                transaction3.commit();
                break;
        }
    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
