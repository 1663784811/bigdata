package com.cyyaw.netty.shorts.client;

import java.util.concurrent.ExecutionException;

public class Client {

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        for (int i = 0; i < 1; i++) {
            String msg = com.cyyaw.netty.shorts.client.NettyClient.sendMessage("127.0.0.1", 10086 ,"dfdfdfdfdfdfdfdf 返回数据 ");
            System.out.println("返回数据--------------------："+ msg);

        }




    }

}
