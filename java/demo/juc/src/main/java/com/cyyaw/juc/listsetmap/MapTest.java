package com.cyyaw.juc.listsetmap;

import java.util.HashMap;

public class MapTest {

    public static void main(String[] args) {

        try {
            //线程不安全
            test01();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private static void test01() {

        HashMap<String, Object> map = new HashMap<>();

        map.put("sss", "ssss");
        map.put("ssss", "ssss");
        map.put("sssss", "ssss");
        map.put("ssddss", "ssss");




    }


}
