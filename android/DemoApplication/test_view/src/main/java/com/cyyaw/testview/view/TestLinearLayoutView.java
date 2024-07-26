package com.cyyaw.testview.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TestLinearLayoutView extends LinearLayout {
    public static final String TAG = TestLinearLayoutView.class.getName();

    public TestLinearLayoutView(Context context) {
        super(context);
    }

    public TestLinearLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestLinearLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestLinearLayoutView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * 测量控件的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setY(400);

        setTranslationY(800);
//        if (getLayoutParams().height == realHeight) {
//            return;
//        }
//        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
//        int windowsHeight = getWindowsHeight((Activity) getContext());
//
//
//        //计算高度
//        int height = measureHeight > windowsHeight ? measureHeight : windowsHeight;
//        totalOffsetY = height - defaultHeight;
//        setY(4000);
//        //设置view收缩状态下距离顶部的间距
//        defaultMarginTop = totalOffsetY;
//        //设置view拉伸状态下距离顶部的间距
//        realMarginTop = height - realHeight;
//        getLayoutParams().height = (int) realHeight;
//        setLayoutParams(getLayoutParams());
//        //新增headerview
//        if (headerView != null) {
//            if (headerView.getParent() == null) {
//                RelativeLayout parent = (RelativeLayout) getParent();
//                parent.addView(headerView);
//            }
//            headerView.setY(totalOffsetY - headerView.getMeasuredHeight());
//        }
//        //新增titleView
//        if (titleViews != null) {
//            for (View titleView : titleViews) {
//                //这里要判断有没有headerview，如果有，需要减去headerview的高度
//                if (headerView != null) {
//                    titleView.setY(totalOffsetY - titleView.getMeasuredHeight() - headerView.getMeasuredHeight());
//                } else {
//                    titleView.setY(totalOffsetY - titleView.getMeasuredHeight());
//                }
//            }
//        }
//        Log.d(TAG, "屏幕高：" + windowsHeight + "     测量高度：" + height + "   defaultHeight：" + defaultHeight + "   realHeight：" + realHeight);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 方法用于处理各种触摸事件，例如点击、长按、滑动等
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }


    //获取屏幕的高度
    public static int getWindowsHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

}
