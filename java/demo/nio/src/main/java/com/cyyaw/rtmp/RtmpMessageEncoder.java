package com.cyyaw.rtmp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * RTMP 消息编码器
 * 根据 RTMP 协议将 RTMP 消息编码为二进制格式
 */
public class RtmpMessageEncoder extends MessageToByteEncoder<RtmpMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RtmpMessage msg, ByteBuf out) throws Exception {
        // 基本 RTMP 消息格式:
        // 消息头 (1-12 字节，基于块头类型)
        // 扩展时间戳 (可选，4 字节)
        // 消息载荷

        // 为简单起见，我们实现一个基本版本
        encodeChunk(out, msg); // 编码块
    }

    /**
     * 编码 RTMP 消息块
     */
    private void encodeChunk(ByteBuf out, RtmpMessage message) {
        // 根据消息类型和块流 ID 确定块头类型
        byte chunkHeader = (byte) (0x00 << 6); // 格式 0 (完整头)
        chunkHeader |= (byte) (message.getChunkStreamId() & 0x3F); // 最后 6 位用于块流 ID

        out.writeByte(chunkHeader); // 写入块头

        // 写入时间戳 (3 字节，大端序)
        // 在格式 0 中，这是绝对时间戳
        out.writeMedium(message.getTimestamp()); // 写入时间戳

        // 写入消息长度 (3 字节，大端序)
        out.writeMedium(message.getLength()); // 写入长度

        // 写入消息类型 ID (1 字节)
        out.writeByte(message.getTypeId()); // 写入类型 ID

        // 写入消息流 ID (4 字节，小端序)
        out.writeIntLE(message.getStreamId()); // 写入流 ID

        // 写入消息载荷
        out.writeBytes(message.getPayload()); // 写入载荷数据
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("Encoder error: " + cause.getMessage()); // 编码器错误
        cause.printStackTrace(); // 打印异常堆栈
        super.exceptionCaught(ctx, cause);
    }
}