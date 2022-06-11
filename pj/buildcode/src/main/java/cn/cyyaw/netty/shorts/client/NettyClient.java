package cn.cyyaw.netty.shorts.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NettyClient {

    /**
     * 发送数据
     *
     * @param host 主机
     * @param port 端口
     * @param msg  发送数据
     * @return
     */
    public static String sendMessage(String host, int port, String msg) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(host, port);
        CountDownLatch latch = new CountDownLatch(1);
        ClientSimpleChannelInboundHandler handler = new ClientSimpleChannelInboundHandler(latch);

        //==========================================================
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LengthFieldPrepender(8, 0, false));
                pipeline.addLast(new LengthFieldBasedFrameDecoder(8388608, 0, 8, 0, 8));
                pipeline.addLast(new StringEncoder(Charset.forName("GB2312")));
                pipeline.addLast(new StringDecoder(Charset.forName("GB2312")));
                pipeline.addLast(handler);
            }
        });
        // =========================================================
        ChannelFuture future = bootstrap.connect();
        future.sync();
        future.await();
        // 发送数据
        future.channel().writeAndFlush(msg);
        latch.await(10, TimeUnit.SECONDS);
        // 关闭流
        future.channel().close();
        group.shutdownGracefully();
        String msgData = handler.getMsg();
        return msgData;
    }

}
