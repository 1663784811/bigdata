package com.cyyaw.demoapplication.task;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.cyyaw.demoapplication.service.FloatWindowLogService;
import com.cyyaw.demoapplication.service.ServerMessage;

import java.util.List;

/**
 * 任务控制线程
 * 启动、停止、暂停
 */
public class ThreadController {


    public static final String TAG = "ThreadController";


    private AccessibilityService accessibilityService;



    /**
     * 启动
     */
    public void start() {


        ThreadTask threadTask = new ThreadTask();
        threadTask.run();






        AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
        CharSequence packageName = rootInActiveWindow.getPackageName();
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = rootInActiveWindow.getActionList();

        Log.d(TAG, "onClick: " + packageName);
        // 开启任务
        ServerMessage.sendMsg(FloatWindowLogService.class, String.valueOf(packageName));


    }
}
