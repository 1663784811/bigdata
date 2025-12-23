package com.cyyaw.rtmp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * RTMP 握手处理器
 * 实现 RTMP 握手协议 (C0, C1, C2, S0, S1, S2)
 */
public class RtmpHandshakeHandler extends ChannelInboundHandlerAdapter {

    private static final int RTMP_HANDSHAKE_SIZE = 1536; // 0x0600 - RTMP 握手包大小
    private static final byte RTMP_VERSION = 0x03; // RTMP 版本号

    /**
     * 握手状态枚举
     */
    private enum HandshakeState {
        WAITING_FOR_C0C1, // 等待 C0 和 C1
        WAITING_FOR_C2,   // 等待 C2
        HANDSHAKE_COMPLETE // 握手完成
    }

    private HandshakeState state = HandshakeState.WAITING_FOR_C0C1; // 当前握手状态
    private byte[] c1Buffer = new byte[RTMP_HANDSHAKE_SIZE + 1]; // C1 缓冲区 (+1 为版本字节)
    private int c1BytesRead = 0; // 已读取的 C1 字节数

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (state == HandshakeState.HANDSHAKE_COMPLETE) {
            // 握手已完成，将消息传递给下一个处理器
            ctx.fireChannelRead(msg);
            return;
        }

        ByteBuf input = (ByteBuf) msg; // 输入缓冲区

        switch (state) {
            case WAITING_FOR_C0C1:
                handleC0C1(ctx, input); // 处理 C0 和 C1
                break;
            case WAITING_FOR_C2:
                handleC2(ctx, input); // 处理 C2
                break;
        }

        // 释放输入缓冲区，因为我们已经处理了它
        input.release();
    }

    /**
     * 处理 C0 和 C1 握手包
     */
    private void handleC0C1(ChannelHandlerContext ctx, ByteBuf input) throws Exception {
        // 读取 C0 (1 字节) 和 C1 (1536 字节)
        if (input.readableBytes() >= RTMP_HANDSHAKE_SIZE + 1) {
            // 读取 C0 - 版本字节
            byte c0 = input.readByte();

            // 读取 C1 - 1536 字节
            input.readBytes(c1Buffer, 0, RTMP_HANDSHAKE_SIZE);
            c1BytesRead = RTMP_HANDSHAKE_SIZE;

            // 验证 C0 版本
            if (c0 != RTMP_VERSION) {
                System.err.println("Invalid RTMP version: " + c0); // RTMP 版本无效
                ctx.close(); // 关闭连接
                return;
            }

            // 发送 S0, S1, S2
            sendS0S1S2(ctx, c1Buffer);

            // 移动到下一个状态
            state = HandshakeState.WAITING_FOR_C2;
        } else {
            // 数据不足，继续读取
            while (input.isReadable()) {
                c1Buffer[c1BytesRead++] = input.readByte();
            }

            if (c1BytesRead >= RTMP_HANDSHAKE_SIZE + 1) {
                // 验证 C0
                if (c1Buffer[0] != RTMP_VERSION) {
                    System.err.println("Invalid RTMP version: " + c1Buffer[0]); // RTMP 版本无效
                    ctx.close();
                    return;
                }

                // 发送 S0, S1, S2
                byte[] actualC1 = new byte[RTMP_HANDSHAKE_SIZE];
                System.arraycopy(c1Buffer, 1, actualC1, 0, RTMP_HANDSHAKE_SIZE);
                sendS0S1S2(ctx, actualC1);

                // 移动到下一个状态
                state = HandshakeState.WAITING_FOR_C2;
            }
        }
    }

    /**
     * 处理 C2 握手包
     */
    private void handleC2(ChannelHandlerContext ctx, ByteBuf input) throws Exception {
        if (input.readableBytes() >= RTMP_HANDSHAKE_SIZE) {
            // 读取 C2 (1536 字节) - 基本实现中不需要针对 C1 进行验证
            input.skipBytes(RTMP_HANDSHAKE_SIZE);

            // 握手完成
            state = HandshakeState.HANDSHAKE_COMPLETE;

            // 从管道中移除此处理器，因为握手已完成
            ctx.pipeline().remove(this);

            System.out.println("RTMP handshake completed successfully"); // RTMP 握手成功完成
        }
    }

    /**
     * 发送 S0, S1, S2 握手包
     */
    private void sendS0S1S2(ChannelHandlerContext ctx, byte[] c1) throws Exception {
        // 发送 S0 (版本)
        ByteBuf s0 = ctx.alloc().buffer(1); // 分配缓冲区
        s0.writeByte(RTMP_VERSION); // 写入版本号
        ctx.writeAndFlush(s0); // 写入并刷新

        // 发送 S1 (1536 字节 - 时间和随机数据)
        ByteBuf s1 = ctx.alloc().buffer(RTMP_HANDSHAKE_SIZE + 1); // 分配缓冲区

        // 写入时间 (4 字节，大端序)
        long time = System.currentTimeMillis(); // 获取当前时间
        s1.writeInt((int) time); // 写入时间戳

        // 写入零 (4 字节)
        s1.writeInt(0); // 写入零值

        // 写入随机数据 (1528 字节)
        Random random = new Random(); // 随机数生成器
        byte[] randomBytes = new byte[RTMP_HANDSHAKE_SIZE - 8]; // 8 字节用于时间和零值
        random.nextBytes(randomBytes); // 生成随机字节
        s1.writeBytes(randomBytes); // 写入随机字节

        ctx.writeAndFlush(s1); // 写入并刷新

        // 发送 S2 (回显 C1)
        ByteBuf s2 = ctx.alloc().buffer(RTMP_HANDSHAKE_SIZE); // 分配缓冲区
        s2.writeBytes(c1); // 写入 C1 的副本
        ctx.writeAndFlush(s2); // 写入并刷新
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("Handshake error: " + cause.getMessage()); // 握手错误
        cause.printStackTrace(); // 打印异常堆栈
        ctx.close(); // 关闭连接
    }
}