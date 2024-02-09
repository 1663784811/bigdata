package com.cyyaw.demoapplication.task;

/**
 * 任务实体
 */
public class TaskEntity {

    // 任务名称
    private String name;

    // 包名
    private String packageName;

    // 页面名
    private String activityName;

    // 状态
    private String status;

    // ====================


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
