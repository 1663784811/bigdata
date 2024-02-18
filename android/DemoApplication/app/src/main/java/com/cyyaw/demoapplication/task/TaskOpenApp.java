package com.cyyaw.demoapplication.task;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.util.AppUtil;


/**
 * 打开APP
 */
public class TaskOpenApp implements Task {


    /**
     * 执行任务
     */
    public void exec( AccessibilityService accessibilityService, TaskEntity taskEntity) {
        AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
        CharSequence packageName = rootInActiveWindow.getPackageName();
        if (packageName.equals(taskEntity.getPackageName())) {

        } else {
            // 点击Home键
            //AppUtil.onKeyHome();
            // 等待0.5秒
            SystemClock.sleep(200);
            // 打到对应的app点击
            // 查找app x y 计算中点


            // AppUtil.onClick(100,100);

        }


    }


}
