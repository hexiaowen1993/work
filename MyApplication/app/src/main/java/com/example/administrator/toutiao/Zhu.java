package com.example.administrator.toutiao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.administrator.toutiao.Fragment.Denlu;
import com.example.administrator.toutiao.Fragment.Fragment_shouye;
import com.example.administrator.toutiao.Fragment.Fragment_yangguang;
import com.example.administrator.toutiao.Fragment.Guanzhu;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


/**
 * Created by Administrator on 2017/3/10.
 */

public class Zhu extends FragmentActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    private FrameLayout layout;
    private RadioButton shouye;
    private RadioButton yangguang;
    private RadioButton guanzhu;
    private RadioButton weidenglu;
    private Fragment_shouye fr_shou;
    private Fragment_yangguang fr_yangguang;
    private Denlu fr_denlu;
    private Guanzhu fr_guanzhu;
    private LinearLayout lin_log;
    private LinearLayout lin_xinxi;
    private ImageView ima;
    private TextView na;
    private LinearLayout duanxin;
    private CheckBox image_yejian;
    private int theme = R.style.AppTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.zhu);
        SMSSDK.initSDK(this, "1c114e195dc60", "8be090490e049501bdc9399e4b0d0cc4");
        //实现侧滑功能
        final SlidingMenu menu = new SlidingMenu(Zhu.this);
        menu.attachToActivity(Zhu.this, SlidingMenu.SLIDING_CONTENT);

        menu.setMenu(R.layout.cela_list);
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindOffset(200);
        lin_log = (LinearLayout) findViewById(R.id.lin_log);
        lin_xinxi = (LinearLayout) findViewById(R.id.lin_xinxi);
        ImageView qq_log = (ImageView) findViewById(R.id.qq_log);
        image_yejian = (CheckBox) findViewById(R.id.image_yejian);



        //夜间模式--切换背景
        image_yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                Zhu.this.recreate();
            }
        });
        //日夜见模式---切换图标
        image_yejian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    image_yejian.setSelected(true);
                }else{
                    image_yejian.setSelected(false);
                }

            }
        });
        /// /实现QQ登录

        mTencent = Tencent.createInstance(APP_ID,Zhu.this.getApplicationContext());

        qq_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(Zhu.this,"all", mIUiListener);
                lin_log.setVisibility(View.INVISIBLE);
                lin_xinxi.setVisibility(View.VISIBLE);
            }
        });
        ima = (ImageView) findViewById(R.id.te_image);
        na = (TextView) findViewById(R.id.te_name);
        ToggleButton ce = (ToggleButton) findViewById(R.id.ce);
        ce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    menu.toggle();
                }
            }
        });
        layout = (FrameLayout) findViewById(R.id.fra_layout);
        shouye = (RadioButton) findViewById(R.id.shouye);
        yangguang = (RadioButton) findViewById(R.id.yangguang);
        guanzhu = (RadioButton) findViewById(R.id.guanzhu);
        weidenglu = (RadioButton) findViewById(R.id.weidenglu);
        //退出QQ
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(Zhu.this);
                builder.setTitle("是否要退出");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lin_log.setVisibility(View.VISIBLE);
                        lin_xinxi.setVisibility(View.INVISIBLE);
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
        });

//手机验证码登录
         ImageView shouji= (ImageView) findViewById(R.id.image_shouji);
         ImageView imageView= (ImageView) findViewById(R.id.lin_shoujiima);
        TextView nam= (TextView) findViewById(R.id.shouji_na);
        duanxin = (LinearLayout) findViewById(R.id.lin_shoujiyanzhen);
         shouji.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //打开注册页面
                 RegisterPage registerPage = new RegisterPage();
                 registerPage.setRegisterCallback(new EventHandler() {
                     public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                         if (result == SMSSDK.RESULT_COMPLETE) {
                             @SuppressWarnings("unchecked")
                             HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                             String country = (String) phoneMap.get("country");
                             String phone = (String) phoneMap.get("phone");

// 提交用户信息（此方法可以不调用）
                            // registerUser(country, phone);
                         }
                     }
                 });
                 registerPage.show(Zhu.this);
                 duanxin.setVisibility(View.VISIBLE);
                 lin_log.setVisibility(View.INVISIBLE);
             }



         });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(Zhu.this);
                builder.setTitle("是否要退出");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        duanxin.setVisibility(View.INVISIBLE);
                        lin_log.setVisibility(View.VISIBLE);
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
        });

        //控制界面的显示与影藏
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fr_shou = new Fragment_shouye();
        fr_yangguang = new Fragment_yangguang();
        fr_denlu = new Denlu();
        fr_guanzhu = new Guanzhu();
        transaction.add(R.id.fra_layout, fr_shou, "f1");
        transaction.add(R.id.fra_layout, fr_yangguang, "f2");
        transaction.add(R.id.fra_layout, fr_denlu, "f3");
        transaction.add(R.id.fra_layout, fr_guanzhu, "f4");
        showAndHidden(fr_shou, fr_yangguang, fr_guanzhu, fr_denlu);
        transaction.commit();

    }
//自动登录
private class BaseUiListener implements IUiListener {

    @Override
    public void onComplete(Object response) {
        Toast.makeText(Zhu.this, "授权成功", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "response:" + response);
        final JSONObject obj = (JSONObject) response;
        try {
            String openID = obj.getString("openid");
            String accessToken = obj.getString("access_token");
            String expires = obj.getString("expires_in");
            mTencent.setOpenId(openID);
            mTencent.setAccessToken(accessToken,expires);
            QQToken qqToken = mTencent.getQQToken();
            mUserInfo = new UserInfo(getApplicationContext(),qqToken);
            mUserInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object response) {
                    Log.e(TAG,"登录成功"+response.toString());
                    //获取QQ头像和qq名字
                    JSONObject object= (JSONObject) response;
                    try {
                        String pic=object.getString("figureurl_qq_1");
                        String name=object.getString("nickname");
                        ImageLoader.getInstance().displayImage(pic,ima);
                        na.setText(name);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onError(UiError uiError) {
                    Log.e(TAG,"登录失败"+uiError.toString());
                }

                @Override
                public void onCancel() {
                    Log.e(TAG,"登录取消");

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {
        Toast.makeText(Zhu.this, "授权失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel() {
        Toast.makeText(Zhu.this, "授权取消", Toast.LENGTH_SHORT).show();

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




    private void showAndHidden(Fragment f1,Fragment f2,Fragment f3,Fragment f4) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(f1);
        transaction.hide(f2);
        transaction.hide(f3);
        transaction.hide(f4);
        transaction.commit();
         shouye.setOnClickListener(this);
        yangguang.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        weidenglu.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shouye:
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                showAndHidden(fr_shou,fr_yangguang,fr_guanzhu,fr_denlu);
                transaction1.commit();
                break;
            case R.id.yangguang:
                FragmentTransaction transaction2= getSupportFragmentManager().beginTransaction();
                showAndHidden(fr_yangguang,fr_shou,fr_guanzhu,fr_denlu);
                transaction2.commit();
                break;
            case R.id.guanzhu:
                FragmentTransaction transaction3= getSupportFragmentManager().beginTransaction();
                showAndHidden(fr_guanzhu,fr_shou,fr_yangguang,fr_denlu);
                transaction3.commit();
                break;
            case R.id.weidenglu:
                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                showAndHidden(fr_denlu,fr_shou,fr_yangguang,fr_guanzhu);
                transaction4.commit();
                break;
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
      //  super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
       // super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
}
