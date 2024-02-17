package com.cyyaw.juc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread thread = new Thread(futureTask);
        thread.start();
        // 取消线程
        // futureTask.cancel(true);
        // 判断线程是否被取消
        System.out.println(futureTask.isCancelled());
        // 判断子线程是否完成
        System.out.println(futureTask.isDone());
        // 获取线程结果,主线程会被阻塞
        String s = futureTask.get();
        System.out.println(s);
    }

}
