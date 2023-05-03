package com.cyyaw.juc.thread;

import cn.hutool.core.util.RandomUtil;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallAbleTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();
        FutureTask[] randomNumberTasks = new FutureTask[5];
        for (int i = 0; i < randomNumberTasks.length; i++) {
            Callable c = new C(i);
            // 执行任务并获取Future对象
            randomNumberTasks[i] = new FutureTask(c);
            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
        }
        int a = RandomUtil.randomInt(100);
        if (a % 2 != 0) {
            System.out.println("------- 主线程  【引用】 子线程 ----------");
            for (Future f : randomNumberTasks) {// 获取所有并发任务的运行结果
                System.out.println(">>>" + f.get().toString()); // 从Future对象上获取任务的返回值，并输
            }
        } else {
            System.out.println("------- 主线程没引用子线程 ----------");

        }
        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}


class C implements Callable<Object> {

    private int taskNum;

    public C(int taskNum) {
        this.taskNum = taskNum;
    }

    // call方法的实现，主要用于执行线程的具体实现，并返回结果
    @Override
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        int ins = RandomUtil.randomInt(15) * 1000;
        Thread.sleep(ins);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println("<<<" + taskNum + "任务完成:【" + time + "毫秒】");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}

