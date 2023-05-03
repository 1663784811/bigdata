package com.cyyaw.juc.thread;

public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("开始：" + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            A a = new A();
            a.start();
        }
        System.out.println("结束：" + Thread.currentThread().getName());
    }
}


class A extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("启动线程" + Thread.currentThread().getName());

    }
}