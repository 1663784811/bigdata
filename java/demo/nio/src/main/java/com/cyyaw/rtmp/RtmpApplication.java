package com.cyyaw.rtmp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * RTMP 服务器应用程序
 * 此类使用 Netty 实现基本的 RTMP 服务器
 */
public class RtmpApplication {

    private final int port;

    public RtmpApplication(int port) {
        this.port = port;
    }

    /**
     * 启动 RTMP 服务器
     */
    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 主线程组，负责接受连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 工作线程组，负责处理IO事件

        try {
            ServerBootstrap bootstrap = new ServerBootstrap(); // 服务器引导程序
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // 使用 NIO 服务器套接字通道
                    .childHandler(new RtmpServerInitializer()) // 设置子通道初始化器
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置连接队列大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // 启用 TCP 保活

            ChannelFuture future = bootstrap.bind(port).sync(); // 绑定端口并同步等待
            System.out.println("RTMP Server started on port: " + port); // 打印启动信息
            future.channel().closeFuture().sync(); // 等待服务器关闭
        } finally {
            workerGroup.shutdownGracefully(); // 优雅关闭工作线程组
            bossGroup.shutdownGracefully(); // 优雅关闭主线程组
        }
    }

    /**
     * RTMP 服务器通道初始化器
     * 为新连接的通道添加处理器
     */
    private static class RtmpServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline(); // 获取通道处理管道
            pipeline.addLast("readTimeout", new ReadTimeoutHandler(30)); // 添加读取超时处理器（30秒超时）
            pipeline.addLast("handshaker", new RtmpHandshakeHandler()); // 添加握手处理器
            pipeline.addLast("decoder", new RtmpMessageDecoder()); // 添加消息解码器
            pipeline.addLast("encoder", new RtmpMessageEncoder()); // 添加消息编码器
            pipeline.addLast("handler", new RtmpServerHandler()); // 添加消息处理器
        }
    }

    /**
     * 程序入口点
     * 根据命令行参数启动 RTMP 服务器
     */
    public static void main(String[] args) {
        int port = 1935; // RTMP 默认端口

        // 检查是否有指定端口参数
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]); // 解析端口号
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number provided. Using default port: " + port); // 端口号格式错误，使用默认端口
            }
        }

        try {
            System.out.println("Starting RTMP server on port " + port + "..."); // 启动服务器
            RtmpApplication rtmpServer = new RtmpApplication(port);
            rtmpServer.start();
        } catch (Exception e) {
            System.err.println("Failed to start RTMP server: " + e.getMessage()); // 服务器启动失败
            e.printStackTrace();
        }
    }
}