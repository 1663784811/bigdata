package com.cyyaw.netty.shorts.client;

import java.util.concurrent.ExecutionException;

public class Client {

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        for (int i = 0; i < 100; i++) {
            String msg = NettyClient.sendMessage("127.0.0.1", 10086 ,"dfdfdfdfdfdfdfdf");
            System.out.println("返回数据--------------------："+ msg);

        }




    }

}
