package com.cyyaw.juc.cas;

import com.cyyaw.instance.User;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {



    public static void main(String[] args) {


        User user1 = new User();
        User user2 = new User();
        AtomicReference<User> at = new AtomicReference();
        at.set(user1);
        System.out.println(at.compareAndSet(user1, user2));


    }

}
