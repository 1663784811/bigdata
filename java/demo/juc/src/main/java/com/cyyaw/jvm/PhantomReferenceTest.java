package com.cyyaw.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

    public static void main(String[] args) throws InterruptedException {

        Object a1 = new Object();
        ReferenceQueue<Object>  list = new ReferenceQueue<>();
        PhantomReference<Object> aa = new PhantomReference<>(a1,list);
        System.out.println( aa.get() );
        System.out.println( list.poll() );

        a1 =null;
        System.gc();
        Thread.sleep(1000L);

        System.out.println( aa.get() );
        System.out.println( list.poll() );


    }
}
