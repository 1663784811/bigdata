package com.cyyaw.rtmp;

/**
 * RTMP 消息类型
 * 根据协议规范定义不同类型的 RTMP 消息
 */
public enum RtmpMessageType {
    CHUNK_SIZE((byte) 0x01),           // 设置块大小
    ABORT((byte) 0x02),               // 中止消息
    ACKNOWLEDGEMENT((byte) 0x03),     // 确认消息
    USER_CONTROL((byte) 0x04),        // 用户控制消息
    WINDOW_ACKNOWLEDGEMENT_SIZE((byte) 0x05), // 窗口确认大小
    SET_PEER_BANDWIDTH((byte) 0x06),  // 设置对等带宽
    AUDIO((byte) 0x08),               // 音频消息
    VIDEO((byte) 0x09),               // 视频消息
    DATA_AMF3((byte) 0x0F),           // 数据消息 (AMF3)
    SHARED_OBJECT_AMF3((byte) 0x10),  // 共享对象消息 (AMF3)
    COMMAND_AMF3((byte) 0x11),        // 命令消息 (AMF3)
    DATA_AMF0((byte) 0x12),           // 数据消息 (AMF0)
    SHARED_OBJECT_AMF0((byte) 0x13),  // 共享对象消息 (AMF0)
    COMMAND_AMF0((byte) 0x14),        // 命令消息 (AMF0)
    AGGREGATE((byte) 0x16);           // 聚合消息

    private final byte typeId; // 类型 ID

    RtmpMessageType(byte typeId) {
        this.typeId = typeId; // 初始化类型 ID
    }

    /**
     * 获取类型 ID
     */
    public byte getTypeId() {
        return typeId; // 返回类型 ID
    }

    /**
     * 根据类型 ID 获取消息类型
     */
    public static RtmpMessageType fromTypeId(byte typeId) {
        for (RtmpMessageType type : values()) { // 遍历所有类型
            if (type.typeId == typeId) { // 检查类型 ID 是否匹配
                return type; // 返回匹配的类型
            }
        }
        return null; // 未找到匹配类型
    }
}