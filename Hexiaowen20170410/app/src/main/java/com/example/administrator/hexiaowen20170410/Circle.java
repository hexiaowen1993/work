package com.example.administrator.hexiaowen20170410;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/10.
 */

public class Circle extends View {

    private Paint paint;
    private Paint paint1;
    //圆环宽度
    private int with = 50/2;
    //圆环半径
    private  float cx = 100+with;
    //圆环中心点
    private  float radius = 100;
    private RectF rectF;
    //最小进度
    private  int mix = 0;
    private  int max = 100;
    private Paint paint2;


    public Circle(Context context) {
        super(context);
        inint();
    }



    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        inint();

    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx,cx,radius,paint);
        float alig = (float) mix/max*360;
        canvas.drawArc(rectF,-90,alig,false,paint1);
        canvas.drawText(mix + "",cx,cx,paint2);
    }
    public void setMax(int max){
        this.max = max;
    }
    public void setProgress(int mix ){
        this.mix = mix;
        postInvalidate();
    }
    private void inint() {
         paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
         paint1=new Paint();
        paint1.setStrokeWidth(10);
        paint1.setAntiAlias(true);
        paint1.setColor(Color.YELLOW);
        paint1.setStyle(Paint.Style.STROKE);
        rectF = new RectF(with,with,radius*2+with,radius*2+with);
        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setAntiAlias(true);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(30);


    }

}
