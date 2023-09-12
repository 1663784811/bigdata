package com.cyyaw.demoapplication.service.window;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.util.PreferenceUtil;

/**
 * 创建一个浮窗
 */
public class FloatWindow extends RelativeLayout {
    private WindowManager.LayoutParams layoutParams;

    private WindowManager windowManager;


    private FloatWindow(Context context) {
        super(context);
    }


    /**
     * 创建一个默认浮窗
     */
    public static FloatWindow crateDefaultWindow(Context context, WindowManager windowManager) {
        return createWindow(context, windowManager, -1, null, null, null);
    }

    public static FloatWindow crateDefaultWindow(Context context, WindowManager windowManager, int layout) {
        return createWindow(context, windowManager, layout, null, null, null);
    }

    public static FloatWindow crateDefaultWindow(Context context, WindowManager windowManager, int layout, Integer left, Integer top) {
        return createWindow(context, windowManager, layout, null, left, top);
    }

    /**
     * 创建一个浮窗
     */
    public static FloatWindow createWindow(Context context, WindowManager windowManager, int layout, WindowManager.LayoutParams params, Integer left, Integer top) {
        FloatWindow floatWindow = new FloatWindow(context);
        floatWindow.setWindowManager(windowManager);
        floatWindow.setFloatWindowParams(context, params, left, top);
        if (layout != -1) {
            LayoutInflater.from(context).inflate(layout, floatWindow);
        } else {
            LayoutInflater.from(context).inflate(R.layout.float_window_layout, floatWindow);
        }
        setOnTouchListener(windowManager, floatWindow, floatWindow.getFloatWindowParams());
        return floatWindow;
    }


    private void setFloatWindowParams(Context context, WindowManager.LayoutParams params, Integer left, Integer top) {
        if (null == params) {
            Point sizePoint = new Point();
            windowManager.getDefaultDisplay().getSize(sizePoint);
            int screenWidth = sizePoint.x;
            int screenHeight = sizePoint.y;
            WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
            windowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            windowParams.format = PixelFormat.RGBA_8888;
            windowParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            windowParams.gravity = Gravity.START | Gravity.TOP;
            windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            if (null != left && null != top) {
                windowParams.x = left;
                windowParams.y = top;
            } else {
                int x = PreferenceUtil.getSingleton(context).getInt("SP_X", -1);
                int y = PreferenceUtil.getSingleton(context).getInt("SP_Y", -1);
                if (x == -1 || y == -1) {
                    x = screenWidth;
                    y = screenHeight / 2;
                }
                windowParams.x = x;
                windowParams.y = y;
            }
            this.layoutParams = windowParams;
        } else {
            this.layoutParams = params;
        }
    }

    public WindowManager.LayoutParams getFloatWindowParams() {
        return this.layoutParams;
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }


    @SuppressLint("ClickableViewAccessibility")
    private static void setOnTouchListener(final WindowManager windowManager, final FloatWindow windowView, WindowManager.LayoutParams windowParams) {
        Point sizePoint = new Point();
        windowManager.getDefaultDisplay().getSize(sizePoint);
        int winX = sizePoint.x;
        int winY = sizePoint.y;
        windowView.setOnTouchListener(new View.OnTouchListener() {
            int lastX, lastY, paramX, paramY;

            // 获取窗口的宽度高度
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        if (windowParams.x <= 0) {
                            paramX = 0;
                        } else if (windowParams.x > winX - windowView.getWidth()) {
                            paramX = winX - windowView.getWidth();
                        } else {
                            paramX = windowParams.x;
                        }
                        if (windowParams.y <= 0) {
                            paramY = 0;
                        } else if (windowParams.y > winY - windowView.getHeight()) {
                            paramY = winY - windowView.getHeight();
                        } else {
                            paramY = windowParams.y;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        int setX = paramX + dx;
                        int setY = paramY + dy;
                        if (setX <= 0) {
                            setX = 0;
                        }
                        if (setX > winX - windowView.getWidth()) {
                            setX = winX - windowView.getWidth();
                        }
                        if (setY <= 0) {
                            setY = 0;
                        }
                        if (setY > winY - windowView.getHeight()) {
                            setY = winY - windowView.getHeight();
                        }
                        windowParams.x = setX;
                        windowParams.y = setY;
                        // 更新悬浮窗位置
                        windowManager.updateViewLayout(windowView, windowParams);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }


}
