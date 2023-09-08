package com.cyyaw.demoapplication.task.hello;

/**
 * 操作任务
 */
public interface TaskOperation {

    /**
     * 任务名称
     */
    String getTaskCode();

    /**
     * 操作
     *
     * @return 返回下一个任务名称， null 则表示 停止
     */
    String operation();

}
