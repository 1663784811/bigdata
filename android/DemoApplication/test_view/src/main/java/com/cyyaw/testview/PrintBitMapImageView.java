package com.cyyaw.testview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrintBitMapImageView extends View {

    private int paperWidth = 58;
    private int printWidth = 48;


    // ==============================
    // 宽度比
    private BigDecimal printWidthRatio;
    // 显示的宽度
    private int showPrintWidth;
    private int showPrintX;
    private int showPrintY;


    // ==============================
    Context context;
    private int width;
    private int height;

    // ==== 背景
    private Paint bgRectFPaint;
    private RectF bgRectF;
    private float bgCornerRadius;


    public PrintBitMapImageView(Context context) {
        super(context);
        init(null);
    }

    public PrintBitMapImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public PrintBitMapImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    public PrintBitMapImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PrintBitMapImageView, 0, 0);
        int pw = typedArray.getInt(R.styleable.PrintBitMapImageView_paperWidth, 58);
        if (pw > 0) {
            paperWidth = pw;
        }
        int printW = typedArray.getInt(R.styleable.PrintBitMapImageView_printWidth, 48);
        if (printW > 0) {
            printWidth = printW;
        }
        // ===========================================================
        // 初始化画笔
        // 白色背景
        // setBackgroundColor(Color.WHITE);
        // 绘制带圆角的矩形背景
        bgRectFPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgRectFPaint.setColor(Color.WHITE); // 设置背景颜色为红色
        bgRectF = new RectF();
        bgCornerRadius = 10 * getResources().getDisplayMetrics().density; // 圆角半径
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(bgRectF, bgCornerRadius, bgCornerRadius, bgRectFPaint);
        // 画矩形
        canvas.drawBitmap(drawRectangle(showPrintWidth, 300), showPrintX, showPrintY, null);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bgRectF.set(0, 0, w, h);
    }


    /**
     * 如果你的视图需要自定义尺寸，你可以覆写 onMeasure 方法。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = 500;
        // 计算有效打印宽度比
        printWidthRatio = new BigDecimal(printWidth).divide(new BigDecimal(paperWidth), 18, RoundingMode.HALF_UP);
        showPrintWidth = printWidthRatio.multiply(new BigDecimal(width)).intValue();
        showPrintX = new BigDecimal(width - showPrintWidth).divide(new BigDecimal(2), 18, RoundingMode.HALF_UP).intValue();
        showPrintY = 50;
        setMeasuredDimension(width, height);
    }

    /**
     * 处理触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 处理按下事件
                return true;
            case MotionEvent.ACTION_MOVE:
                // 处理移动事件
                return true;
            case MotionEvent.ACTION_UP:
                // 处理抬起事件
                return true;
        }
        return super.onTouchEvent(event);
    }


    /**
     * 画矩形
     */
    private Bitmap drawRectangle(int w, int h) {
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas cs = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //paint.setStrokeWidth(10); // 画笔大小
        paint.setStyle(Paint.Style.FILL); // 设置画笔样式为填充
        // 在 Bitmap 上绘制一条红色的对角线
        cs.drawRect(0, 0, w, h, paint);
        return bitmap;
    }

}
