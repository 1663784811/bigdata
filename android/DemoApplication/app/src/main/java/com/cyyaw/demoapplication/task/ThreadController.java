package com.cyyaw.demoapplication.task;

import android.accessibilityservice.AccessibilityService;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.cyyaw.demoapplication.activity.MainActivity;
import com.cyyaw.demoapplication.service.FloatWindowLogService;
import com.cyyaw.demoapplication.util.AppUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 任务控制线程
 * 启动、停止、暂停
 */
public class ThreadController {
    public static final String TAG = "ThreadController";
    private volatile AccessibilityService accessibilityService;

    // ===========================================

    // 桌面包名
    private static final String deskHome = "com.miui.home";
    // 任务状态
    private volatile int status = 0;
    // 任务列表
    private volatile List<TaskContainer> taskList = null;

    private volatile Thread taskThread;
    // ===========================================


    public ThreadController(AccessibilityService accessibilityService) {
        this.accessibilityService = accessibilityService;
        taskList = new CopyOnWriteArrayList<>();
        TaskContainer taskContainer = new TaskContainer();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("打开小红书");
        taskEntity.setPackageName("com.xingin.xhs");
        taskEntity.setActivityName("sss");
        taskContainer.addTask(taskEntity);
        taskList.add(taskContainer);
    }

    /**
     * 启动
     */
    public void start() {

        if (status == 0) {
            status = 1;
            Toast.makeText(accessibilityService, "正在启动任务" + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
            taskThread = new Thread(() -> {
                for (int i = 0; i < taskList.size(); i++) {
                    TaskContainer task = taskList.get(i);
                    int execNum = task.getExecNum();
                    int num = 0;
                    while ((execNum - num) > 0) {
                        num++;
                        task.exec(accessibilityService);
                    }
                    // ========================
                    SystemClock.sleep(1000);
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
            });
            taskThread.start();
        } else {
            status = 0;
            Toast.makeText(accessibilityService, "正在停止任务" + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
            taskThread.interrupt();
        }
////        ThreadTask threadTask = new ThreadTask();
////        threadTask.run();
//
//        //  ==============================================
//        AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
//        CharSequence packageName = rootInActiveWindow.getPackageName();
//        List<AccessibilityNodeInfo.AccessibilityAction> actionList = rootInActiveWindow.getActionList();
//        Log.d(TAG, "onClick: " + packageName);
//        // 开启任务
//        ServerMessage.sendMsg(FloatWindowLogService.class, String.valueOf(packageName));
//        traverseLayout(rootInActiveWindow);


    }



    /**
     * 获取窗口信息
     */
    public void getWinInfo() {
        AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
        CharSequence packageName = rootInActiveWindow.getPackageName();
        // 获取包名
        accessibilityService.sendBroadcast(new Intent(FloatWindowLogService.class.getName()).putExtra("data", packageName));

    }


}
