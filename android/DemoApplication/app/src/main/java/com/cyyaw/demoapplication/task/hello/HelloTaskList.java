package com.cyyaw.demoapplication.task.hello;

public enum HelloTaskList {
    HelloTaskList("com.ssssedd.sees", "桌面:找到helloApp图标,点击打开");
    // 任务Code
    private String code;
    // 任务名称
    private String note;

    HelloTaskList(String code, String note) {
        this.code = code;
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }
}
