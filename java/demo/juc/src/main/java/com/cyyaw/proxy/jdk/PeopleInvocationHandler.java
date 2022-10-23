package com.cyyaw.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PeopleInvocationHandler implements InvocationHandler {


    private Object peolple;

    public PeopleInvocationHandler(Object people) {
        this.peolple = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object invoke = method.invoke(peolple, args);
        System.out.println("-------- end ---------");
        return invoke;
    }
}
