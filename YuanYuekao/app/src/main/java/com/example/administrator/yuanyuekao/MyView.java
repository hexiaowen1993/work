package com.example.administrator.yuanyuekao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class MyView extends View {
    private SetOnWhat sow;

    private int one;
    private int two;
    private int three;

    private int RX;
    private int RY;
    private float width;
    private int rect;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = getWidth();
        int y = getHeight();

        rect = x*3/4;
        int R = rect*8/25;
        width = R*3/8;

        RX = x/2;
        RY = y/2;

        int r = rect/2 - (int) (width*2);

        one = r;
        two = (int) (r+width);
        three = (int)(r+width*2);

        int left = (x - rect)/2;
        int right = left + rect;
        int top = (y - rect)/2;
        int bottom = top + rect;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        paint.setStrokeWidth(width);
        paint.setAntiAlias(true);

        canvas.drawRect(left, top, right, bottom, paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(RX, RY, r, paint);

        paint.setStyle(Paint.Style.STROKE);

        paint.setColor(Color.RED);

        canvas.drawCircle(RX, RY, R, paint);

        paint.setColor(Color.BLUE);

        canvas.drawCircle(RX, RY, R+width, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        int action = event.getAction();

        if(action==MotionEvent.ACTION_DOWN||action==MotionEvent.ACTION_MOVE){
            if (x + getLeft() < getRight() && y + getTop() < getBottom()) {
                int type = 0;
                int distance = getDistance(x, y);

                if(distance<=one*one){
                    type = 0;
                }else if(distance>one&&distance<=two*two){
                    type = 1;
                }else if(distance>two&&distance<=three*three){
                    type = 2;
                }else if(x>=(RX - rect/2)&&x<=(RX + rect/2)&&y>=(RY - rect/2)&&y<=(RY+rect/2)){
                    type = 3;
                }else {
                    type = 4;
                }

                if(sow!=null){
                    sow.onWhat(type);
                }
            }
        }
        return true;
    }

    private int getDistance(int x, int y){
        return (x-RX)*(x-RX) + (y-RY)*(y-RY);
    }

    public interface SetOnWhat{
        void onWhat(int type);
    }

    public void getOnWhat(SetOnWhat sow){
        this.sow = sow;
    }

}