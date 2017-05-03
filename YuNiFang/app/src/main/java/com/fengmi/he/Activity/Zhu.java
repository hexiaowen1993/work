package com.fengmi.he.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fengmi.he.FragmentPakage.Fragment_Me;
import com.fengmi.he.FragmentPakage.Fragment_che;
import com.fengmi.he.FragmentPakage.Fragment_fenlei;
import com.fengmi.he.FragmentPakage.Fragment_meila;
import com.fengmi.he.FragmentPakage.Fragment_shouye;
import com.fengmi.he.R;

//import static com.fengmi.he.R.id.meila;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Zhu extends FragmentActivity implements View.OnClickListener{

    private Fragment_shouye fr_shouye;
    private Fragment_fenlei fr_fenglei;
    private Fragment_meila fr_meila;
    private Fragment_che che;
    private Fragment_Me me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhu);
        FrameLayout layout = (FrameLayout) findViewById(R.id.fralayout);
        RadioGroup rg= (RadioGroup) findViewById(R.id.group);
        RadioButton shouye = (RadioButton) findViewById(R.id.shouye);
        RadioButton fenlei = (RadioButton) findViewById(R.id.fenlei);
        //RadioButton meila = (RadioButton) findViewById(R.id.meila);
        RadioButton gouwu = (RadioButton) findViewById(R.id.gouwu);
        RadioButton wode = (RadioButton) findViewById(R.id.wode);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fr_shouye = new Fragment_shouye();
        fr_fenglei = new Fragment_fenlei();
        fr_meila = new Fragment_meila();
        che = new Fragment_che();
        me = new Fragment_Me();

        transaction.add(R.id.fralayout, fr_shouye, "f1");
        transaction.add(R.id.fralayout, fr_fenglei, "f2");
       // transaction.add(R.id.fralayout, fr_meila, "f3");
        transaction.add(R.id.fralayout, che, "f4");
        transaction.add(R.id.fralayout, me, "f5");
        setShowAndHide(fr_shouye, fr_fenglei, fr_meila, che, me);
        transaction.commit();

        //设置点击事件
        //RadioGroup的默认选中第一页
        rg.check(R.id.shouye);
        shouye.setOnClickListener(this);
        fenlei.setOnClickListener(this);
       // meila.setOnClickListener(this);
        gouwu.setOnClickListener(this);
         wode.setOnClickListener(this);
    }

    private void setShowAndHide(Fragment f1, Fragment f2, Fragment f3, Fragment f4, Fragment f5) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //fragment动画
            transaction.setCustomAnimations(R.anim.traslet_in,R.anim.tralet_out);
        transaction.show(f1);
        transaction.hide(f2);
        transaction.hide(f3);
        transaction.hide(f4);
        transaction.hide(f5);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shouye:
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                setShowAndHide(fr_shouye,fr_fenglei,fr_meila,che,me);
                transaction.commit();
                break;
            case R.id.fenlei:
                FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
                setShowAndHide(fr_fenglei,fr_shouye,fr_meila,che,me);
                transaction1.commit();
                break;

            case R.id.gouwu:
                FragmentTransaction transaction3=getSupportFragmentManager().beginTransaction();
                setShowAndHide(che,fr_meila,fr_fenglei,fr_shouye,me);
                transaction3.commit();
                break;
            case R.id.wode:
                FragmentTransaction transaction4=getSupportFragmentManager().beginTransaction();
                setShowAndHide(me,che,fr_meila,fr_fenglei,fr_shouye);
                transaction4.commit();
                break;
        }
    }
}
