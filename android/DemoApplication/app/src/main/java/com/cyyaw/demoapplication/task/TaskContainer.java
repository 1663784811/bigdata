package com.cyyaw.demoapplication.task;

import android.accessibilityservice.AccessibilityService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskContainer {

    /**
     * 执行次数据
     */
    private volatile int execNum = 1;

    private volatile List<TaskEntity> taskList = new CopyOnWriteArrayList<>();

    /**
     * 执行任务
     */
    void exec(AccessibilityService accessibilityService) {
        for (int i = 0; i < taskList.size(); i++) {
            TaskEntity taskEntity = taskList.get(i);
            String packageName = taskEntity.getPackageName();
            String pk = accessibilityService.getPackageName();
            if (!packageName.equals(pk)) {
                // 不在当前的package

            }
        }
    }


    /**
     * 添加任务
     */
    public void addTask(TaskEntity taskEntity) {
        taskList.add(taskEntity);
    }


    // ============================================
    public int getExecNum() {
        return execNum;
    }

    public void setExecNum(int execNum) {
        this.execNum = execNum;
    }

    public List<TaskEntity> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskEntity> taskList) {
        this.taskList = taskList;
    }
}
