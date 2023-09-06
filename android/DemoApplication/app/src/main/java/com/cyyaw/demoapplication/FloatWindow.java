package com.cyyaw.demoapplication;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.cyyaw.demoapplication.util.PreferenceUtil;

/**
 * 创建一个浮窗
 */
public class FloatWindow extends RelativeLayout {

    public int viewWidth;
    public int viewHeight;

    private WindowManager.LayoutParams layoutParams;

    private WindowManager windowManager;


    private FloatWindow(Context context) {
        super(context);
    }


    /**
     * 创建一个默认浮窗
     */
    public static FloatWindow crateDefaultWindow(Context context, WindowManager windowManager) {
        return createWindow(context, windowManager, null);
    }

    /**
     * 创建一个浮窗
     */
    public static FloatWindow createWindow(Context context, WindowManager windowManager, WindowManager.LayoutParams params) {

        FloatWindow floatWindow = new FloatWindow(context);
        floatWindow.setWindowManager(windowManager);
        floatWindow.setFloatWindowParams(context, params);
        LayoutInflater.from(context).inflate(R.layout.float_window_layout, floatWindow);
        return floatWindow;
    }


    private void setFloatWindowParams(Context context, WindowManager.LayoutParams params) {
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
            int x = PreferenceUtil.getSingleton(context).getInt("SP_X", -1);
            int y = PreferenceUtil.getSingleton(context).getInt("SP_Y", -1);
            if (x == -1 || y == -1) {
                x = screenWidth;
                y = screenHeight / 2;
            }
            windowParams.x = x;
            windowParams.y = y;

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

}
