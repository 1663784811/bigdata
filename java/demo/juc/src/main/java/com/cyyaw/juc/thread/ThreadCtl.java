package com.cyyaw.juc.thread;

public class ThreadCtl {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("==============  线程被中断");
                    break;
                } else {
                    System.out.println("==============  线程正常运行");
                }
            }
        });
        t1.start();
        new Thread(() -> {
            t1.interrupt();
        }).start();
    }
}
