package com.cyyaw.demoapplication.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;

public class FloatWindowService extends AccessibilityService implements View.OnClickListener {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;


    @Override
    public void onCreate() {
        Log.i("ssssssssssssssssssssssssssssss", "seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        Context context = getApplicationContext();
        createWindow(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }


    /**
     * 创建窗口
     */
    private void createWindow(Context context) {
        wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
        layoutParams = floatWindow.getFloatWindowParams();
        floatWindow.findViewById(R.id.btnWinInfo).setOnClickListener(this);
        wManager.addView(floatWindow, layoutParams);
    }


    @Override
    public void onClick(View v) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
            CharSequence packageName = rootInActiveWindow.getPackageName();
            Log.i("========== 当前窗口包名", packageName.toString());
            traverseLayout(rootInActiveWindow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void traverseLayout(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }

        // 获取节点的类名
        CharSequence className = nodeInfo.getClassName();

        // 获取节点的文本内容
        CharSequence text = nodeInfo.getText();

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
        Log.d("AccessibilityService", "Left: " + left + ", Top: " + top + ", Right: " + right + ", Bottom: " + bottom);

        // 在这里处理节点信息，例如打印到日志
        Log.d("AccessibilityService", "Class: " + className + ", Text: " + text + ", ID: " + viewId);

        // 递归遍历子节点
        for (int i = 0; i < nodeInfo.getChildCount(); i++) {
            traverseLayout(nodeInfo.getChild(i));
        }
    }
}
