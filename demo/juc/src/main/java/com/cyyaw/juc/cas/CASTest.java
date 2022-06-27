package com.cyyaw.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {


    public static void main(String[] args) {
        AtomicInteger number = new AtomicInteger(0);


        if(number.compareAndSet(0, 2)){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }

        System.out.println("当前值："+ number);

        if(number.compareAndSet(0, 2)){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }

        System.out.println("当前值："+ number);




        number.getAndAdd(1);



    }
}


