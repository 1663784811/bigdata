package com.cyyaw.testview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CuiPopoverView extends ConstraintLayout {

    private static final String TAG = CuiPopoverView.class.getName();

    private Paint paint;
    private Path path;

    private int width;
    private int height;


    public CuiPopoverView(Context context) {
        super(context);
        init(context);
    }

    public CuiPopoverView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CuiPopoverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CuiPopoverView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
//        addView(new CuiTriangleView(context));

        paint = new Paint();
        paint.setColor(0xFF000000); // 黑色
        paint.setStyle(Paint.Style.FILL);
        path = new Path();
        width = 100;
        height = 100;


        setOnClickListener((View v) -> {
            Log.e(TAG, "init: " );
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(width / 2f, 0); // 顶点
        path.lineTo(0, height); // 左下角
        path.lineTo(width, height); // 右下角
        path.close(); // 闭合路径
        canvas.drawPath(path, paint);
    }











}
