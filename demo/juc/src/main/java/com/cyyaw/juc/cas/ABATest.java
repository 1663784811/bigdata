package com.cyyaw.juc.cas;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 * 原子引用
 *
 * 版本号原子引用
 *
 *
 */


public class ABATest {

    public static void main(String[] args) {
        //  =========================    原子引用
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User user1 = new User();
        User user2 = new User();
        userAtomicReference.set(user1);
        boolean b = userAtomicReference.compareAndSet(user1, user2);
        if(b){
            System.out.println("============== 成功");
        }else{
            System.out.println("============== 失败");
        }
        boolean c = userAtomicReference.compareAndSet(user1, user2);
        if(c){
            System.out.println("============== 成功");
        }else{
            System.out.println("============== 失败");
        }

        //  =========================    版本号原子引用
        AtomicStampedReference<User> user = new AtomicStampedReference<>(user1,1);
        int stamp = user.getStamp();
        boolean b1 = user.compareAndSet(user1, user2, stamp, stamp+1);


    }

}



@Data
class User{

    private String name;
    private int age;

}