package com.cyyaw.demoapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class SquareView extends View {

    private Paint paint;

    public SquareView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取视图的宽度和高度
        int width = getWidth();
        int height = getHeight();
        // 计算正方形的边长（取宽度和高度的较小值）
        int size = Math.min(width, height);
        // 计算正方形的左上角坐标
        int left = (width - size) / 2;
        int top = (height - size) / 2;
        // 绘制红色正方形
        canvas.drawRect(left, top, left + size, top + size, paint);
    }
}
