package com.fengmi.he.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.fengmi.he.Bean.OrderBean;
import com.fengmi.he.R;
import com.fengmi.he.Util.PayResult;
import com.fengmi.he.Util.PayUtils;


public class UpOrderActivity extends AppCompatActivity {
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(UpOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(UpOrderActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(UpOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhifu);
        Intent intent = getIntent();
        OrderBean orderBean = (OrderBean) intent.getSerializableExtra("orderBean");
        Log.d("xml", orderBean.toString());
        ImageView sure= (ImageView) findViewById(R.id.zhifubao);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alipay();
            }
        });
    }
    private void alipay() {
        String orderinfo = PayUtils.getOrderInfo("测试商品", "购买一部手机", "0.01");
        String signinfo = PayUtils.getSignInfo(orderinfo);
        final String totalInfo = PayUtils.getTotalInfo(orderinfo, signinfo);

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(UpOrderActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(totalInfo, true);
                Log.i("TAG", "走了pay支付方法.............");

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
