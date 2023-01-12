package com.cyyaw.netty.shorts.server;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteOrder;
import java.util.List;

/**
 * 解码器
 * <p>
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * +  固定值  |   长度位   |    数据
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
@Slf4j
public class AnShanDecoder extends ByteToMessageDecoder {

    /**
     * 大端、小端
     */
    private static ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

    /**
     * 最大8MB
     */
    private static int maxLength = 1024 * 1024 * 8;

    /**
     * 长度位偏移
     */
    private static int lengthFieldOffset = 0;

    /**
     * 长度字节宽度
     */
    private static int lengthFieldLength;


    /**
     * 解码
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 可读长度
        int readLength = in.readableBytes();
        if (readLength > maxLength) {
            throw new CorruptedFrameException("数据太大无法接收: 最大为" + maxLength + "个字节,实际为" + readLength + "个字节");
        }
        // 判断是否可读取
        if (in.readableBytes() < (lengthFieldOffset + lengthFieldLength)) {
            return;
        }
        in.order(byteOrder);
        // 读取长度位
        byte[] lengthFieldByte = new byte[lengthFieldLength];
        in.getBytes(lengthFieldOffset, lengthFieldByte);
        String lengthData = new String(lengthFieldByte, "GBK");
        log.info("数据：{}", lengthData);
        int length = Integer.getInteger(lengthData);
        if (readLength < length) {
            log.info("===============  数据还没到全 =======================");
            return;
        }

        int index = 0;
        // 读取数据
        ByteBuf frame = in.retainedSlice(index, length);
        // 移动下标
        in.readerIndex(index + length);
        out.add(frame);

    }


}
