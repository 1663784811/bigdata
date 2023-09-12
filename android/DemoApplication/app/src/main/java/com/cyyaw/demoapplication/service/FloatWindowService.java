package com.cyyaw.demoapplication.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.R;
import com.cyyaw.demoapplication.service.window.FloatWindow;
import com.cyyaw.demoapplication.task.AutoHelloTask;

import java.util.List;

public class FloatWindowService extends AccessibilityService implements View.OnClickListener {

    private WindowManager wManager;

    private FloatWindow floatWindow;

    private WindowManager.LayoutParams layoutParams;

    private Context context;


    @Override
    public void onCreate() {
        Log.i("ssssssssssssssssssssssssssssss", "seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        context = getApplicationContext();
        createWindow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        if (eventType == 222) {


            CharSequence packageName = event.getPackageName();
            showWindowInfo("onAccessibilityEvent:" + packageName);
        }

    }

    @Override
    public void onInterrupt() {

    }


    /**
     * 创建窗口
     */
    private void createWindow() {
        if (wManager == null) {
            wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            floatWindow = FloatWindow.crateDefaultWindow(context, wManager);
            layoutParams = floatWindow.getFloatWindowParams();
            floatWindow.findViewById(R.id.btnWinInfo).setOnClickListener(this);
            wManager.addView(floatWindow, layoutParams);
        }
    }


    @Override
    public void onClick(View v) {
        try {


            AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
            CharSequence packageName = rootInActiveWindow.getPackageName();
            List<AccessibilityNodeInfo.AccessibilityAction> actionList = rootInActiveWindow.getActionList();




            // 开启任务
            AutoHelloTask.start(this);

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
        if ("微信".equals(text)) {
            // 点击打开微信
            AccessibilityNodeInfo parent = nodeInfo.getParent();
            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            showWindowInfo("点击：" + text);
//            SquareView square = AppUtil.createSquare(wManager, context, parent);
        }
        // 递归遍历子节点
        for (int i = 0; i < nodeInfo.getChildCount(); i++) {
            traverseLayout(nodeInfo.getChild(i));
        }
        // ========================================================
    }

    // TODO  通信方式有问题
    private void showWindowInfo(String msg) {
        Intent serviceIntent = new Intent(this, FloatWindowInfoService.class);
        serviceIntent.putExtra(FloatWindowInfoService.logKey, msg);
        startService(serviceIntent);
    }

}
