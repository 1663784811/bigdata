package com.cyyaw.netty.shorts.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.CountDownLatch;

public class ClientSimpleChannelInboundHandler extends SimpleChannelInboundHandler<String> {

    private volatile String msg;

    private volatile CountDownLatch latch;

    public ClientSimpleChannelInboundHandler(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接收数据："+ msg);
        this.msg = msg;
        latch.countDown();
    }

    public String getMsg() {
        return this.msg;
    }
}
