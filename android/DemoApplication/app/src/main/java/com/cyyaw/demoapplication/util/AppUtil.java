package com.cyyaw.demoapplication.util;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.data.NodeInfo;
import com.cyyaw.demoapplication.data.NodeInfoCenterXY;
import com.cyyaw.demoapplication.view.SquareView;

public class AppUtil {

    private static Instrumentation inst = new Instrumentation();

    private AppUtil() {
    }


    public static void aaa(AccessibilityNodeInfo nodeInfo) {

        // 获取节点的ID
        CharSequence viewId = nodeInfo.getViewIdResourceName();
        // 获取元素的矩形坐标
        Rect boundsInScreen = new Rect();
        nodeInfo.getBoundsInScreen(boundsInScreen);
        // 现在，boundsInScreen 包含元素在屏幕上的坐标信息
        int left = boundsInScreen.left;
        int top = boundsInScreen.top;
        int right = boundsInScreen.right;
        int bottom = boundsInScreen.bottom;


        NodeInfo info = new NodeInfo();

        return;
    }

    /**
     * 计算中点
     */
    public static NodeInfoCenterXY getCenterXY(int left, int top, int right, int bottom) {
        int x = left + (right - left) / 2;
        int y = top + (bottom - top) / 2;
        NodeInfoCenterXY nodeInfoCenterXY = new NodeInfoCenterXY();
        nodeInfoCenterXY.setX(x);
        nodeInfoCenterXY.setY(y);
        return nodeInfoCenterXY;
    }

    /**
     * 移动窗口
     */
    public static void moveWindow() {

    }

    /**
     * 创建正方形
     */
    public static SquareView createSquare(WindowManager wManager, Context context, AccessibilityNodeInfo nodeInfo) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        int left = rect.left;
        int top = rect.top;
        int right = rect.right;
        int bottom = rect.bottom;
        SquareView squareView = new SquareView(context);
        // 设置 LayoutParams 来指定视图的位置和大小
        WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
        windowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        windowParams.format = PixelFormat.RGBA_8888;
        windowParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        windowParams.gravity = Gravity.START | Gravity.TOP;
        windowParams.width = right - left;
        windowParams.height = bottom - top;
        windowParams.x = left;
        windowParams.y = top;
        //
        wManager.addView(squareView, windowParams);
        return squareView;
    }

}
