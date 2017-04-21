package com.fengmi.he.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/4/13.
 */

public class MyGriView extends GridView {
    public MyGriView(Context context) {
        super(context);
    }

    public MyGriView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGriView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
