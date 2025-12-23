package com.cyyaw.rtmp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * RTMP 消息解码器
 * 根据 RTMP 协议将二进制 RTMP 数据解码为 RTMP 消息
 */
public class RtmpMessageDecoder extends ReplayingDecoder<RtmpMessageDecoder.State> {

    private RtmpChunkHeader currentChunkHeader; // 当前块头
    private RtmpMessage currentMessage; // 当前消息
    private byte[] messageBuffer; // 消息缓冲区
    private int messageBytesRead = 0; // 已读取消息字节数

    /**
     * 解码器状态枚举
     */
    public enum State {
        READ_CHUNK_HEADER,    // 读取块头
        READ_MESSAGE_PAYLOAD  // 读取消息载荷
    }

    public RtmpMessageDecoder() {
        super(State.READ_CHUNK_HEADER); // 初始状态为读取块头
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case READ_CHUNK_HEADER:
                decodeChunkHeader(in); // 解码块头
                checkpoint(State.READ_MESSAGE_PAYLOAD); // 设置检查点为读取消息载荷
            case READ_MESSAGE_PAYLOAD:
                decodeMessagePayload(in, out); // 解码消息载荷
                checkpoint(State.READ_CHUNK_HEADER); // 设置检查点为读取块头
                break;
        }
    }

    /**
     * 解码块头
     */
    private void decodeChunkHeader(ByteBuf in) {
        // 读取块基本头 (1 字节)
        byte basicHeader = in.readByte(); // 读取基本头
        int format = (basicHeader >> 6) & 0x03; // 前 2 位为格式
        int chunkStreamId = basicHeader & 0x3F; // 后 6 位为块流 ID

        // 基本实现，我们处理格式 0 (11 字节头)
        int timestamp = in.readMedium(); // 3 字节时间戳
        int length = in.readMedium(); // 3 字节长度
        byte typeId = in.readByte(); // 1 字节类型 ID
        int streamId = in.readIntLE(); // 4 字节流 ID，小端序

        // 创建当前块头对象
        currentChunkHeader = new RtmpChunkHeader(format, chunkStreamId, timestamp, length, typeId, streamId);
        messageBuffer = new byte[currentChunkHeader.getLength()]; // 分配消息缓冲区
        messageBytesRead = 0; // 重置已读取字节数
    }

    /**
     * 解码消息载荷
     */
    private void decodeMessagePayload(ByteBuf in, List<Object> out) {
        int remaining = currentChunkHeader.getLength() - messageBytesRead; // 剩余字节数
        int toRead = Math.min(remaining, in.readableBytes()); // 要读取的字节数

        in.readBytes(messageBuffer, messageBytesRead, toRead); // 读取数据到缓冲区
        messageBytesRead += toRead; // 更新已读取字节数

        if (messageBytesRead == currentChunkHeader.getLength()) {
            // 完整消息接收完成
            currentMessage = new RtmpMessage(
                currentChunkHeader.getChunkStreamId(), // 块流 ID
                currentChunkHeader.getTimestamp(),     // 时间戳
                currentChunkHeader.getLength(),        // 长度
                currentChunkHeader.getTypeId(),        // 类型 ID
                currentChunkHeader.getStreamId(),      // 流 ID
                messageBuffer                        // 载荷数据
            );

            out.add(currentMessage); // 添加到输出列表

            // 为下一条消息重置
            currentMessage = null; // 清空当前消息
            messageBuffer = null; // 清空消息缓冲区
            messageBytesRead = 0; // 重置已读取字节数
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("Decoder error: " + cause.getMessage()); // 解码器错误
        cause.printStackTrace(); // 打印异常堆栈
        super.exceptionCaught(ctx, cause);
    }
}