package com.cyyaw.demoapplication.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.task.AppInfo;

import cn.hutool.core.util.StrUtil;

public abstract class ScreenOperation extends AccessibilityService {


    @Override
    public void onInterrupt() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    // =============================================


    /**
     * 点击Home
     */
    public void keyHome() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
    }

    /**
     * 返回按键
     */
    public void back() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }


    /**
     * 点击
     */
    public void clickAtXY(int x, int y) {
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("构建点击: %s,%s", x, y)));
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 2, y + 2);
        dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0, 100)).build(), null, null);
    }

    /**
     * 划动
     */
    public void performSwipeLeft(int x1, int y1, int x2, int y2, int duration) {
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", String.format("构建向左滑动手势: %s,%s - %s,%s", x1, y1, x2, y2)));
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 0, duration));
        // 发送手势事件
        dispatchGesture(gestureBuilder.build(), null, null);
    }


    // =============================================

    /**
     * 打开APP
     *
     * @return
     */
    public boolean openApp(AppInfo appInfo) {
        AccessibilityNodeInfo windowRoot = getRootInActiveWindow();
        CharSequence pk = windowRoot.getPackageName();
        sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", "打开:" + appInfo.getAppName()));
        if (!appInfo.getPackageName().equals(pk)) {
            // 不在当前的package
            keyHome();
            SystemClock.sleep(100);
            AccessibilityNodeInfo nodeInfo = findNodeInfoByName(appInfo.getAppName());
            if (nodeInfo != null) {
                AccessibilityNodeInfo parent = nodeInfo.getParent();
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                SystemClock.sleep(300);
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    // =============================================
    public AccessibilityNodeInfo findNodeInfoByName(String name) {
        return traverseLayout(getRootInActiveWindow(), name);
    }

    /**
     * 遍历布局文件
     */
    public AccessibilityNodeInfo traverseLayout(AccessibilityNodeInfo nodeInfo, String byName) {
        if (nodeInfo != null) {
            // 获取节点的文本内容
            CharSequence text = nodeInfo.getText();
            // 获取节点的类名
            CharSequence className = nodeInfo.getClassName();
            Rect bounds = new Rect();
            nodeInfo.getBoundsInScreen(bounds);
            int left = bounds.left;
            int top = bounds.top;
            int bottom = bounds.bottom;
            int right = bounds.right;
            System.out.println("节点在屏幕上的左上角坐标：(" + className + "--" + text + "---左" + left + ", 上" + top + "  右:" + right + "  下:" + bottom + ")");

            if (StrUtil.isNotBlank(byName) && byName.equals(text)) {
                return nodeInfo;
            }
            // 递归遍历子节点
            for (int i = 0; i < nodeInfo.getChildCount(); i++) {
                AccessibilityNodeInfo rest = traverseLayout(nodeInfo.getChild(i), byName);
                if (null != rest) {
                    return rest;
                }
            }
            // ========================================================
        }
        return null;
    }

}
