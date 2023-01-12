package com.cyyaw.netty.shorts.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接收数据:"+msg);


        ChannelFuture future = ctx.channel().writeAndFlush(msg);

        ChannelFuture future1 = future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                boolean success = future.isSuccess();
                System.out.println("是否成功:" + success + "    返回的数据:" + msg);
            }
        });




    }


}
