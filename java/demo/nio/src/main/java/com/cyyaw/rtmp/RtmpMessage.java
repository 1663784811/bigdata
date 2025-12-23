package com.cyyaw.rtmp;

/**
 * RTMP 消息类
 * 表示具有所有必要字段的 RTMP 消息
 */
public class RtmpMessage {
    private final int chunkStreamId; // 块流 ID
    private final int timestamp; // 时间戳
    private final int length; // 长度
    private final byte typeId; // 类型 ID
    private final int streamId; // 流 ID
    private final byte[] payload; // 载荷数据

    /**
     * 构造 RTMP 消息
     */
    public RtmpMessage(int chunkStreamId, int timestamp, int length, byte typeId, int streamId, byte[] payload) {
        this.chunkStreamId = chunkStreamId; // 块流 ID
        this.timestamp = timestamp; // 时间戳
        this.length = length; // 长度
        this.typeId = typeId; // 类型 ID
        this.streamId = streamId; // 流 ID
        this.payload = payload; // 载荷数据
    }

    public int getChunkStreamId() {
        return chunkStreamId; // 获取块流 ID
    }

    public int getTimestamp() {
        return timestamp; // 获取时间戳
    }

    public int getLength() {
        return length; // 获取长度
    }

    public byte getTypeId() {
        return typeId; // 获取类型 ID
    }

    public int getStreamId() {
        return streamId; // 获取流 ID
    }

    public byte[] getPayload() {
        return payload; // 获取载荷数据
    }

    /**
     * 获取消息类型
     */
    public RtmpMessageType getMessageType() {
        return RtmpMessageType.fromTypeId(typeId); // 根据类型 ID 获取消息类型
    }

    @Override
    public String toString() {
        return String.format("RtmpMessage{type=%s, streamId=%d, length=%d, timestamp=%d}", // 消息字符串表示
            getMessageType(), streamId, length, timestamp); // 格式化消息信息
    }
}