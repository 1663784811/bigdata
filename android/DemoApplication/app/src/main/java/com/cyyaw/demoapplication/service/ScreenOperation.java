package com.cyyaw.demoapplication.service;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.task.ThreadController;

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
     * 打开APP
     */
    public void openApp(String packageName) {
        new Thread(()->{
            AccessibilityNodeInfo windowRoot = getRootInActiveWindow();
            CharSequence pk = windowRoot.getPackageName();
            if (!packageName.equals(pk)) {
                // 不在当前的package
                keyHome();
                SystemClock.sleep(1000);
                windowRoot = getRootInActiveWindow();
                ThreadController.traverseLayout(windowRoot);
            }
        }).start();

    }

}
