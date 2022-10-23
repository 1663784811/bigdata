package com.cyyaw.netty.shorts.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;


@Slf4j
public class Server {


    public static void main(String[] args) throws InterruptedException {

        Integer port = 10086;

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LengthFieldPrepender(8,0, false));
                pipeline.addLast(new LengthFieldBasedFrameDecoder(8888888, 0, 8,0,8));
                pipeline.addLast(new StringEncoder(Charset.forName("GB2312")));
                pipeline.addLast(new StringDecoder(Charset.forName("GB2312")));

                pipeline.addLast(new SimpleServerHandler());
            }
        });
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.bind(port).sync();
        log.info("服务器启动成功, 端口:{}", port);
    }


}
