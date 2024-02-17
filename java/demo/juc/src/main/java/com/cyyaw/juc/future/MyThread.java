package com.cyyaw.juc.future;

import java.util.concurrent.Callable;

public class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "ssssss";
    }
}
