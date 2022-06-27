package com.cyyaw.juc.listsetmap;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {


    public static void main(String[] args) {
        try {
            // 线程不安全
            test001();
        } catch (Exception e) {
            e.getMessage();
        }

        test002();
        test003();


    }

    private static void test003() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    set.add(UUID.randomUUID().toString());
                    System.out.println(set);
                }
            }).start();
        }
    }

    private static void test002() {

        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    set.add(UUID.randomUUID().toString());
                    System.out.println(set);
                }
            }).start();
        }
    }


    // Exception in thread "Thread-5" Exception in thread "Thread-9" Exception in thread "Thread-8" java.util.ConcurrentModificationException
    private static void test001() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    set.add(UUID.randomUUID().toString());
                }
            }).start();
        }
    }


}
