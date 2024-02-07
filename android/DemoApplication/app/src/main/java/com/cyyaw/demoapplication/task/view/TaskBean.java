package com.cyyaw.demoapplication.task.view;



public class TaskBean {


    /**
     * 任务名
     */
    private String name;


    /**
     * 备注
     */
    private String note;


    public TaskBean(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
