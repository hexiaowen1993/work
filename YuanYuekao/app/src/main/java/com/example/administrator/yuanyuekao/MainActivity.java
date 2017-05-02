package com.example.administrator.yuanyuekao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv_color);
        setText("内圆");
        MyView cv = (MyView) findViewById(R.id.cv);

        cv.getOnWhat(new MyView.SetOnWhat() {
            @Override
            public void onWhat(int type) {
                switch (type) {
                    case 0:
                        Toast.makeText(MainActivity.this, "这里是白色", Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "这里是红色", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,OtherRecycle.class));
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "这里是蓝色", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,RecycleV.class));
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "这里是绿色", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "这..这里是哪里?", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void setText(String message){
        tv.setText(message);
    }

}