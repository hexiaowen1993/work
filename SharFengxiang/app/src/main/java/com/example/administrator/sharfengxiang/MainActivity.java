package com.example.administrator.sharfengxiang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import uk.co.senab.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {
    // 默认是日间模式
    private int theme = R.style.AppTheme;
    private GestureDetector mGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否有主题存储
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);

        Button fen= (Button) findViewById(R.id.fen);
        Button qq= (Button) findViewById(R.id.qq);
        Button riye= (Button) findViewById(R.id.riye);
       PhotoView pic= (PhotoView) findViewById(R.id.pic);

        mGestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if (Math.abs(velocityY) < 100) {
                    // System.out.println("手指移动的太慢了");
                    return true;
                }

                // 手势向下 down
                if ((e2.getRawY() - e1.getRawY()) > 200) {


                    Intent intent=new Intent(MainActivity.this,Next.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
                    finish();//在此处控制关闭
                    return true;
                }
                // 手势向上 up
                if ((e1.getRawY() - e2.getRawY()) > 200) {
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });

        //实现侧拉
        SlidingMenu menu=new SlidingMenu(MainActivity.this);
        menu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.cela);
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindOffset(200);


    riye.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
        MainActivity.this.recreate();
    }
});

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}

