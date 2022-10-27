package com.cyyaw.jvm;

import java.lang.reflect.Method;

public class ClassLoaderTest {


    public static void main(String[] args) throws Exception {

        String codePath="E:\\code\\bigdata\\java\\demo\\juc\\target\\classes";
        MyClassLoader myClassLoader=new MyClassLoader(codePath);
        Class<?> aClass = myClassLoader.loadClass("com.cyyaw.jvm.AA");
        System.out.println("测试字节码是由"+aClass.getClassLoader().getClass().getName()+"加载的。。");
        //利用反射实例化对象，和调用TwoNum类里面的twoNum方法
        Object o = aClass.newInstance();
        Method twoNum = aClass.getDeclaredMethod("add");
        Object invoke = twoNum.invoke(o);
        System.out.println(invoke);
    }




}
