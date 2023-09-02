package com.cyyaw.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {



    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        try {

            boolean  tryLock = reentrantLock.tryLock();
            if(tryLock){


            }

        } finally {

            reentrantLock.unlock();
        }


    }
















}
