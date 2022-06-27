package com.cyyaw.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * jdk 默认提供的线程池
 */
public class DefaultThreadPool {

    private static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);

    private static ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

    private static ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {


        newFixedThreadPool.submit(() -> {

            System.out.println("-------------------------------");

        });


        newSingleThreadExecutor.submit(() -> {

            System.out.println("-------------------------------");

        });

        newCachedThreadPool.submit(() -> {

            System.out.println("-------------------------------");

        });
    }


}
