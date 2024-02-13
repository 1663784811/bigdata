package com.cyyaw.demoapplication.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {

    private static final String TAG = "MyView";

    /**
     * 在new
     */
    public MyView(Context context) {
        super(context);
        Log.d(TAG, "MyView: new 会被调用");
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyView: 在xml中会被调用");
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "MyView: 自定义样式调用");
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "MyView: MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes");
    }


    /**
     * 自定义view的测量方法
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 布局的宽高都是由这个方法指定的
        // 指定控件的宽高

    }


}
