package com.cyyaw.demoapplication.task;

import android.accessibilityservice.AccessibilityService;

public interface Task {


    /**
     * 执行任务
     */
    void exec(AccessibilityService accessibilityService, TaskEntity taskEntity);

}
