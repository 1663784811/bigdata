package com.cyyaw.netty;

public class NettyApplication {

    public static void main(String[] args) throws InterruptedException {
        RtspServer rtspServer = new RtspServer(8554);
        rtspServer.start();
    }
}