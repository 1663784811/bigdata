package com.cyyaw.juc.volatiletest;


/**
 * volatile  是java提供的轻量级的同步机制
 * <p>
 * 特点：
 * 1。可见性：
 * 2。不保证原子性
 * 3。禁止指令重排序
 */

public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {

        MyData myData = new MyData();
        //=========================1。可见性：
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                myData.setNumber();
                System.out.println("========================== 设置" + myData.getNumber());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (myData.getNumber() == 0) {
        }
        System.out.println("========================== 退出程序");


        //=========================2。不保证原子性
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.add();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("========================== 值: " + myData.getNumber());

        //=========================3。禁止指令重排序



    }
}


class MyData {

    private volatile Integer number = 0;

    public void setNumber() {
        this.number = 10;
    }

    public Integer getNumber() {
        return number;
    }

    public void add() {
        number++;
    }
}

