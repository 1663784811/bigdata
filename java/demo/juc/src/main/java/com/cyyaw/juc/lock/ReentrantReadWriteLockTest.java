package com.cyyaw.juc.lock;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {

        MyData myData = new MyData();


        for (int i = 0; i < 20; i++) {

            new Thread(() -> {

            }).start();

        }


    }


}


@Data
class MyData {

    private volatile Map<String, Object> data = new HashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public Object read(String key) {
        Object o = null;
        lock.readLock().lock();
        o = data.get(key);
        lock.readLock().unlock();
        return o;
    }


    public void write(String key, Object v) {
        lock.writeLock().lock();
        data.put(key, v);
        lock.writeLock().unlock();
    }


}