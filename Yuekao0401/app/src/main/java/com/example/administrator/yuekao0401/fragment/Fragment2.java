package com.example.administrator.yuekao0401.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.yuekao0401.MainActivity;
import com.example.administrator.yuekao0401.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/1.
 */

public class Fragment2 extends Fragment {

    private Button qq;
    private Button fen;
    private Button tui;
    private ImageView pic;
    private TextView name;

    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    public static BaseUiListener mIUiListener;
  private UserInfo mUserInfo;

    //private ShareUiListener mIUiListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.disanfang, null);
        qq = (Button) v.findViewById(R.id.qq);
        fen = (Button) v.findViewById(R.id.fen);
        tui = (Button) v.findViewById(R.id.tui);
        pic = (ImageView) v.findViewById(R.id.pic);
        name = (TextView) v.findViewById(R.id.myname);

        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, getActivity().getApplicationContext());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //qq登录
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(getActivity(), "all", mIUiListener);
            }
        });

        //qq分享
        fen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle params = new Bundle();
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//分享的类型
                params.putString(QQShare.SHARE_TO_QQ_TITLE, "你好");//分享标题
                params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"月考");//要分享的内容摘要
                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"http://blog.csdn.net/sandyran");//内容地址
                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://avatar.csdn.net/B/3/F/1_sandyran.jpg");//分享的图片URL
                params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试");//应用名称
                mTencent.shareToQQ(getActivity(), params, mIUiListener);

            }
        });


    }

    //QQ登录
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            final JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());

                        JSONObject object = (JSONObject) response;
                        try {
                            String nameurl = object.getString("nickname");
                            String picurl = object.getString("figureurl_qq_2");
                            name.setText(nameurl);
                            Glide.with(getActivity()).load(picurl).into(pic);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();

        }

    }


}
