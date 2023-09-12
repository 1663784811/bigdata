package com.cyyaw.demoapplication.task.hello;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 桌面
 */
public class HelloDesktopTask implements TaskOperation {

    public String getTaskCode() {
        return HelloTaskList.HelloTaskList.getCode();
    }


    /**
     *  操作
     *  1. 找到 app
     *  2. 打开 app
     */
    public String operation(  ) {


//        AccessibilityNodeInfo rootInActiveWindow = autoHelloTask.accessibilityService.getRootInActiveWindow();
//
//
//        CharSequence packageName = rootInActiveWindow.getPackageName();
//        showWindowInfo("当前窗口包名:" + packageName.toString());
//        traverseLayout(rootInActiveWindow);



        return "";
    }


}
