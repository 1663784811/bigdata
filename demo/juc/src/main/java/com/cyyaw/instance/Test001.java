package com.cyyaw.instance;

import java.lang.reflect.Constructor;

public class Test001 {


    public static void main(String[] args) throws Exception{
        Constructor<User> declaredConstructor = User.class.getDeclaredConstructor();
        User user = declaredConstructor.newInstance();
        Class<?> aClass = User.class.getClassLoader().loadClass("com.cyyaw.instance.User");
        Constructor<?> declaredConstructor1 = aClass.getDeclaredConstructor();



    }

}
