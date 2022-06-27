package com.cyyaw.juc.lock;


/**
 * 重入锁
 */
public class ReentryLock {

    //测试
    public static void main(String agr[]) throws InterruptedException {
        //执行测试,因为synchronized为重入锁,因此不会出现死锁的情况
        new Thread(() -> {
            new ReentryLock().outOne();
        }).start();
        Thread.sleep(2000);
    }


    public synchronized void outOne() {
        System.out.println("One");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //进入该方法内时已经获取到了锁(this),此时再调用outTwo()方法时会自动获取到锁(this)
        outTwo();
    }

    public synchronized void outTwo() {
        System.out.println("Two");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
