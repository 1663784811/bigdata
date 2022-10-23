package com.cyyaw.juc.threadpool;


import java.util.concurrent.*;

/**
 * 线程池的7大参数
 */
public class ThreadPoolExecutorTest {


    public static void main(String[] args) {

        //1.  除非设置了{@code allowCoreThreadTimeOut}，即使它们处于空闲状态也要保留在池中的线​​程数
        int corePoolSize = 10;
        //2. corePoolSize除非设置了{@code allowCoreThreadTimeOut}，即使它们处于空闲状态也要保留在池中的线​​程数
        int maximumPoolSize = 20;
        //3. 当线程数大于内核数时，这是多余的空闲线程将在终止之前等待新任务的最长时间。
        long keepAliveTime = 10L;
        //4. {@code keepAliveTime}参数的时间单位
        TimeUnit seconds = TimeUnit.SECONDS;
        //5. 在执行任务之前用于保留任务的队列。该队列将仅保存由{@code execute}方法提交的{@code Runnable}任务。
        LinkedBlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>(10);
        //6. 执行程序创建新线程时要使用的工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //7. 由于达到线程边界和队列容量而在执行被阻止时使用的处理程序
        RejectedExecutionHandler defaultHandler = new ThreadPoolExecutor.AbortPolicy();
        
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                seconds,
                runnables,
                threadFactory,
                defaultHandler
        );


        //  threadPoolExecutor.submit();


    }


}
