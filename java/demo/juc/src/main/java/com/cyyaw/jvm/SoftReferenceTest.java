package com.cyyaw.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftReferenceTest {


    public static void main(String[] args) {

        String aa = "软引用";

        SoftReference<String> str = new SoftReference<>(aa);
        System.out.println(  str.get()  );


        String bb = "弱引用";
        WeakReference<String> str1 = new WeakReference<>(bb);
        System.out.println(  str1.get() );







    }

}
