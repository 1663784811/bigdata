package com.cyyaw.demoapplication.task;


import com.cyyaw.demoapplication.task.hello.HelloDesktopTask;
import com.cyyaw.demoapplication.task.hello.TaskOperation;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自动接单任务
 */
public class AutoHelloTask {
    public volatile static boolean taskStatus = false;

    public ConcurrentHashMap<String, TaskOperation> taskList;

    public AutoHelloTask() {
        // 初始化任务列表
        HelloDesktopTask helloDesktopTask = new HelloDesktopTask();
        taskList.put(helloDesktopTask.getTaskCode(), helloDesktopTask);


    }


    /**
     * 开始任务
     */
    public void starTask() {


        while (AutoHelloTask.taskStatus) {
            // 判断当前显示内容
            String taskCode = getScreamContent();
            TaskOperation taskOperation = taskList.get(taskCode);
            // 执行操作
            if (null != taskOperation) {
                taskOperation.operation();
            } else {
                // 返回桌面
            }
        }


    }

    public String getScreamContent() {


        return "桌面";
    }


}
