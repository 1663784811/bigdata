package com.cyyaw.demoapplication.task;


import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.cyyaw.demoapplication.task.hello.HelloDesktopTask;
import com.cyyaw.demoapplication.task.hello.TaskOperation;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自动接单任务
 */
public class AutoHelloTask {
    private volatile static AutoHelloTask autoHelloTask;


    // ===============================================================
    private AccessibilityService accessibilityService;
    public boolean taskStatus = false;
    public ConcurrentHashMap<String, TaskOperation> taskList = new ConcurrentHashMap<>();

    // ===============================================================
    private AutoHelloTask() {
        // 初始化任务列表
        HelloDesktopTask helloDesktopTask = new HelloDesktopTask();
        taskList.put(helloDesktopTask.getTaskCode(), helloDesktopTask);
    }


    /**
     * 启动任务
     */
    public static void start(AccessibilityService accessibilityService) {
        ReentrantLock reentrantLock = new ReentrantLock();
        if (autoHelloTask == null) {
            // 加锁
            reentrantLock.lock();
            if (autoHelloTask == null) {
                autoHelloTask = new AutoHelloTask();
            }
            // 解锁
            reentrantLock.unlock();
        }
        // ==============
        if (!autoHelloTask.taskStatus) {
            autoHelloTask.taskStatus = true;
            autoHelloTask.accessibilityService = accessibilityService;
            autoHelloTask.starTask();
        } else {
            Toast.makeText(accessibilityService, "请先停止任务", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 停止任务
     */
    private static void stop() {
        if (autoHelloTask != null && autoHelloTask.taskStatus) {
            autoHelloTask.taskStatus = false;
            Toast.makeText(autoHelloTask.accessibilityService, "正在停止任务...", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始任务
     */
    public void starTask() {
        while (autoHelloTask.taskStatus) {
            // 判断当前显示内容


            AccessibilityNodeInfo rootInActiveWindow = autoHelloTask.accessibilityService.getRootInActiveWindow();




            CharSequence packageName = rootInActiveWindow.getPackageName();
//            showWindowInfo("当前窗口包名:" + packageName.toString());
//            traverseLayout(rootInActiveWindow);
//
            // ===========================================


            String taskCode = getScreamContent();
            TaskOperation taskOperation = taskList.get(taskCode);
            // 执行操作
            if (null != taskOperation) {
                taskOperation.operation();
            } else {
                // 返回桌面
            }
        }
        // =========================
        if (autoHelloTask.taskStatus) {
            Toast.makeText(autoHelloTask.accessibilityService, "任务已完成", Toast.LENGTH_SHORT).show();
            autoHelloTask.taskStatus = false;
        } else {
            Toast.makeText(autoHelloTask.accessibilityService, "任务已停止", Toast.LENGTH_SHORT).show();
        }

    }

    public String getScreamContent() {


        return "桌面";
    }


}
