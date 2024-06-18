package com.cyyaw.testwebrtc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running;
    private Paint paint;

    public DrawingThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        running = false;
        // 创建画笔
        paint = new Paint();
        // 设置颜色
        paint.setColor(Color.RED);
        // 设置宽度
        paint.setStrokeWidth(5);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                // 获取画册布
                canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    synchronized (surfaceHolder) {
                        drawSomething(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    /**
     * 画线
     */
    private void drawSomething(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }
}
