package com.cyyaw.netty.shorts.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext ctx;
    private ChannelPromise promise;
    private com.cyyaw.netty.shorts.client.NettyMessage response;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        com.cyyaw.netty.shorts.client.NettyMessage message = (com.cyyaw.netty.shorts.client.NettyMessage) msg;
        if (message != null && message.getType() == "ss") {
            response = message;
            promise.setSuccess();
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public synchronized ChannelPromise sendMessage(Object message) {
        while (ctx == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                log.error("等待ChannelHandlerContext实例化");
            } catch (InterruptedException e) {
                log.error("等待ChannelHandlerContext实例化过程当中出错",e);
            }
        }
        promise = ctx.newPromise();
        ctx.writeAndFlush(message);
        return promise;
    }

    public com.cyyaw.netty.shorts.client.NettyMessage getResponse(){
        return response;
    }

}

