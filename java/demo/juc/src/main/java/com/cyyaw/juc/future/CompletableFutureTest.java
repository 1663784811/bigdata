package com.cyyaw.juc.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            System.out.println("1111");
            int aa = 1 / 0;
        }).whenCompleteAsync((v, e) -> {
            if (null == e) {
                int aa = 1 / 0;
                System.out.println("1111  的结果:" + v);
            } else {
                System.out.println("1111  的结果: 异常");
            }
        }).exceptionally(e -> {
            System.out.println("异常");
            e.printStackTrace();
            return null;
        });
        CompletableFuture<Void> runAsync2 = CompletableFuture.runAsync(() -> {
            System.out.println("22222");
        }, threadPool).whenCompleteAsync((v, e) -> {
            if (null == e) {
                System.out.println("22222  的结果:" + v);
            }
        }, threadPool);
        CompletableFuture<String> supplyAsync3 = CompletableFuture.supplyAsync(() -> {
            return "33333";
        }).whenCompleteAsync((v, e) -> {
            if (null == e) {
                System.out.println("33333  的结果:" + v);
            }
        }, threadPool);
        CompletableFuture<String> supplyAsync4 = CompletableFuture.supplyAsync(() -> {
            return "4444";
        }, threadPool).whenCompleteAsync((v, e) -> {
            if (null == e) {
                System.out.println("4444  的结果:" + v);
            }
        });
        System.out.println("=================");
        System.out.println(runAsync.get());
        System.out.println(runAsync2.get());
        System.out.println(supplyAsync3.get());
        System.out.println(supplyAsync4.get());
        threadPool.shutdown();
        // =========================
    }

}



