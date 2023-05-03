package com.cyyaw.juc.thread;


public class RunnableTest {

    public static void main(String[] args) {
        System.out.println("开始：" + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            B b = new B();
            new Thread(b).start();
        }
        System.out.println("结束：" + Thread.currentThread().getName());
    }
}


class B implements Runnable {
    @Override
    public void run() {
        System.out.println("启动线程" + Thread.currentThread().getName());
    }
}