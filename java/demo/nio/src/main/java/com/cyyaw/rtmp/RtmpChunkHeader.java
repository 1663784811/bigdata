package com.cyyaw.rtmp;

/**
 * RTMP 块头类
 * 表示 RTMP 块的头部信息
 */
public class RtmpChunkHeader {
    private final int format;        // 格式
    private final int chunkStreamId; // 块流 ID
    private final int timestamp;     // 时间戳
    private final int length;        // 长度
    private final byte typeId;       // 类型 ID
    private final int streamId;      // 流 ID

    /**
     * 构造 RTMP 块头
     */
    public RtmpChunkHeader(int format, int chunkStreamId, int timestamp, int length, byte typeId, int streamId) {
        this.format = format;        // 格式
        this.chunkStreamId = chunkStreamId; // 块流 ID
        this.timestamp = timestamp;  // 时间戳
        this.length = length;        // 长度
        this.typeId = typeId;        // 类型 ID
        this.streamId = streamId;    // 流 ID
    }

    public int getFormat() {
        return format; // 获取格式
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
}