package cn.cyyaw.netty.shorts.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Autowired
    private NettyClientService service;

    @Autowired
    private NettyClient nettyClient;

    /**
     * @Description: 服务端发生消息给客户端，会触发该方法进行接收消息
     * @Author:杨攀
     * @Since: 2019年9月12日下午5:03:31
     * @param ctx
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        String msg = byteBuf.toString(CharsetUtil.UTF_8);
        log.info("客户端收到消息：{}", msg);
        service.ackSyncMsg(msg); // 同步消息返回
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("请求连接成功...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接被断开...");
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(new Runnable() {
            @Override
            public void run() {
                nettyClient.start();
            }
        }, 20, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    /**
     * 处理异常, 一般将实现异常处理逻辑的Handler放在ChannelPipeline的最后
     * 这样确保所有入站消息都总是被处理，无论它们发生在什么位置，下面只是简单的关闭Channel并打印异常信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }

}
