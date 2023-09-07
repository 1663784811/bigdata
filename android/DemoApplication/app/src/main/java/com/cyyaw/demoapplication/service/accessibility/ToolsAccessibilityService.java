package com.cyyaw.demoapplication.service.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;


/**
 * 辅助工具
 */
public class ToolsAccessibilityService extends AccessibilityService {


    @Override
    public void onCreate() {
        super.onCreate();


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        Log.d("==========================", ":::" + action);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onServiceConnected() {

        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        CharSequence packageName = rootInActiveWindow.getPackageName();
        Log.i("========== 当前窗口包名", packageName.toString());

        CharSequence paneTitle = rootInActiveWindow.getPaneTitle();
        Log.i("========== 标题", paneTitle.toString());

        super.onServiceConnected();
        Log.d("==========================", "O(∩_∩)O~~\r\n红包锁定中...");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        Log.d("==========================", ":::::::::::::::::::::");
        if (eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
            // 读取文本内容
            CharSequence text = (CharSequence) event.getText();
            if (text != null) {
                // 在这里处理读取到的文本内容
                String content = text.toString();
            }

        }
    }

    @Override
    public void onInterrupt() {

    }


    /**
     * 获取窗口信息
     */
    public void aaa() {


    }


}
