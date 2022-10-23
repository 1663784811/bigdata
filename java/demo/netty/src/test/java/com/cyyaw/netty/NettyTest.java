package com.cyyaw.netty;

import io.netty.util.internal.SystemPropertyUtil;

public class NettyTest {

    public static void main(String[] args) {
        int DEFAULT_MAX_PENDING_EXECUTOR_TASKS = Math.max(16, SystemPropertyUtil.getInt("io.netty.eventexecutor.maxPendingTasks", 2147483647));
        System.out.println(DEFAULT_MAX_PENDING_EXECUTOR_TASKS);
    }

}
