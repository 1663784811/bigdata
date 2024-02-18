package com.cyyaw.demoapplication.task;

import android.accessibilityservice.AccessibilityService;


/**
 * 任务容器
 */
public interface Task {


    /**
     * 执行任务
     */
    void exec(AccessibilityService accessibilityService, TaskEntity taskEntity);

}
