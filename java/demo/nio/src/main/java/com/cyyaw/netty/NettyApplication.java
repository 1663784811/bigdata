package com.cyyaw.netty;

import com.cyyaw.nio.RtspServer;


public class NettyApplication {

    public static void main(String[] args) throws InterruptedException {
        RtspServer rtspServer = new RtspServer(8554);
        rtspServer.start();
    }
}