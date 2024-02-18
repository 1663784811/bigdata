package com.cyyaw.demoapplication.service;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;


/**
 * 标记
 */
public class FloatMarkWindowService extends BaseService implements View.OnClickListener {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;


    @Override
    public void onCreate() {
        Context context = getApplicationContext();
        createWindow(context);
    }


    /**
     * 创建窗口
     */
    private void createWindow(Context context) {
        WindowManager wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        FloatWindow floatWindow = FloatWindow.crateDefaultWindow(context, wManager, R.layout.mark_circle);

        WindowManager.LayoutParams layoutParams = floatWindow.getFloatWindowParams();
        wManager.addView(floatWindow, layoutParams);

        floatWindow.findViewById(R.id.marker_circle).setOnClickListener(this);

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
        showWindowInfo("AccessibilityService:" + className + "====" + text);
        // 递归遍历子节点
        for (int i = 0; i < nodeInfo.getChildCount(); i++) {
            traverseLayout(nodeInfo.getChild(i));
        }


        // ========================================================


    }

    private void showWindowInfo(String msg) {
//        Intent serviceIntent = new Intent(this, FloatWindowInfoService.class);
//        serviceIntent.putExtra(FloatWindowInfoService.logKey, msg);
//        startService(serviceIntent);
    }


    @Override
    public void onClick(View v) {


        Log.d("AccessibilityService", "Left: ");

    }
}
