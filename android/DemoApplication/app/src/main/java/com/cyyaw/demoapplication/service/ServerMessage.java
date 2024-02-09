package com.cyyaw.demoapplication.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerMessage {

    private static volatile List<BaseService> baseServiceList = new CopyOnWriteArrayList<>();

    private ServerMessage(){}


    public static void register(BaseService baseService) {
        baseServiceList.add(baseService);
    }

    /**
     * 发送数据
     */
    public static void sendMsg(Class clazz, String message){



        // 接收
        for (int i = 0; i < baseServiceList.size(); i++) {
            BaseService baseService = baseServiceList.get(i);
            Class aClass = baseService.getClass();
            if(clazz.getName().equals(aClass.getName())){
                baseService.receiveMsg(message);
            }
        }
    }


}
