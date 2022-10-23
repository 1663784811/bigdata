package com.cyyaw.juc.listsetmap;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {


    public static void main(String[] args) {
        try {
            //线程不安全
            test01();
        } catch (Exception e) {
            e.getMessage();
        }
        test02();   // 解决001
        test03();   // 解决002
        test04();   // 解决003
    }

    private static void test04() {
        CopyOnWriteArrayList<String> arr = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    System.out.println(arr);
                    arr.add(UUID.randomUUID().toString());
                }
            }).start();
        }
    }


    private static void test03() {
        List<String> arr = new Vector<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    System.out.println(arr);
                    arr.add(UUID.randomUUID().toString());
                }
            }).start();
        }
    }


    private static void test02() {
        List<String> arrs = new ArrayList<>();
        List<String> arr = Collections.synchronizedList(arrs);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    System.out.println(arr);
                    arr.add(UUID.randomUUID().toString());
                }
            }).start();
        }
    }

    public static void test01() {
        //   Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException:    线程不安全
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    //System.out.println(arr);
                    arr.add(UUID.randomUUID().toString());
                }
            }).start();
        }
    }


}
