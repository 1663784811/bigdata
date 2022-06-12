package com.cyyaw.spider.thread;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 数据处理线程池
 */
public class DataThreadPool extends AbstractExecutorService {



    public void shutdown() {

    }

    public List<Runnable> shutdownNow() {
        return null;
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void execute(Runnable command) {

    }
}
