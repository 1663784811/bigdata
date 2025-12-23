package com.cyyaw.rtmp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * RTMP 服务器处理器
 * 处理 RTMP 命令并维护连接状态
 */
public class RtmpServerHandler extends SimpleChannelInboundHandler<RtmpMessage> {

    /**
     * RTMP 连接状态枚举
     */
    private enum RtmpState {
        HANDSHAKE_COMPLETE,  // 握手完成
        CONNECT_SENT,       // 连接已发送
        CONNECTED,          // 已连接
        STREAM_CREATED,     // 流已创建
        PUBLISHING          // 发布中
    }

    private RtmpState state = RtmpState.HANDSHAKE_COMPLETE; // 当前连接状态
    private int transactionId = 1; // 事务 ID
    private int streamId = 1; // 流 ID
    private String appName = ""; // 应用名称
    private String streamName = ""; // 流名称

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RtmpMessage msg) throws Exception {
        System.out.println("Received RTMP message: " + msg); // 接收到 RTMP 消息

        switch (msg.getMessageType()) {
            case COMMAND_AMF0:
                handleCommand(ctx, msg); // 处理 AMF0 命令
                break;
            case COMMAND_AMF3:
                handleCommand(ctx, msg); // 处理 AMF3 命令
                break;
            case USER_CONTROL:
                handleUserControl(ctx, msg); // 处理用户控制消息
                break;
            case WINDOW_ACKNOWLEDGEMENT_SIZE:
                handleWindowAckSize(ctx, msg); // 处理窗口确认大小
                break;
            case CHUNK_SIZE:
                handleChunkSize(ctx, msg); // 处理块大小
                break;
            default:
                System.out.println("Unhandled message type: " + msg.getMessageType()); // 未处理的消息类型
                break;
        }
    }

    /**
     * 处理命令消息
     */
    private void handleCommand(ChannelHandlerContext ctx, RtmpMessage msg) {
        // 解析 AMF0 命令
        ByteBuf payload = Unpooled.wrappedBuffer(msg.getPayload()); // 包装载荷数据

        try {
            String commandName = readAmfString(payload); // 读取命令名称
            double transactionId = payload.readDouble(); // 读取事务 ID

            System.out.println("Command: " + commandName + ", Transaction: " + transactionId); // 打印命令信息

            switch (commandName) {
                case "connect":
                    handleConnectCommand(ctx, payload, transactionId); // 处理连接命令
                    break;
                case "createStream":
                    handleCreateStreamCommand(ctx, transactionId); // 处理创建流命令
                    break;
                case "publish":
                    handlePublishCommand(ctx, payload, transactionId); // 处理发布命令
                    break;
                case "play":
                    handlePlayCommand(ctx, payload, transactionId); // 处理播放命令
                    break;
                default:
                    System.out.println("Unknown command: " + commandName); // 未知命令
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error parsing AMF command: " + e.getMessage()); // 解析 AMF 命令错误
            e.printStackTrace();
        }
    }

    /**
     * 处理连接命令
     */
    private void handleConnectCommand(ChannelHandlerContext ctx, ByteBuf payload, double transactionId) {
        // 读取命令对象
        Map<String, Object> commandObject = readAmfObject(payload); // 读取命令对象
        appName = (String) commandObject.get("app"); // 获取应用名称

        // 发送 _result 响应
        sendConnectResult(ctx, transactionId); // 发送连接结果

        state = RtmpState.CONNECTED; // 更新状态为已连接
    }

    /**
     * 处理创建流命令
     */
    private void handleCreateStreamCommand(ChannelHandlerContext ctx, double transactionId) {
        // 发送包含流 ID 的 _result 响应
        sendCreateStreamResult(ctx, transactionId, streamId); // 发送创建流结果
    }

    /**
     * 处理发布命令
     */
    private void handlePublishCommand(ChannelHandlerContext ctx, ByteBuf payload, double transactionId) {
        // 读取流名称和类型
        streamName = readAmfString(payload); // 读取流名称
        String publishType = readAmfString(payload); // "live", "record", "append" 读取发布类型

        System.out.println("Publish request for stream: " + streamName + ", type: " + publishType); // 发布请求

        // 发送 onStatus "NetStream.Publish.Start"
        sendOnStatus(ctx, streamId, "NetStream.Publish.Start", "Publishing started"); // 发送发布开始状态

        state = RtmpState.PUBLISHING; // 更新状态为发布中
    }

    /**
     * 处理播放命令
     */
    private void handlePlayCommand(ChannelHandlerContext ctx, ByteBuf payload, double transactionId) {
        // 读取流名称
        streamName = readAmfString(payload); // 读取流名称

        System.out.println("Play request for stream: " + streamName); // 播放请求

        // 发送 onStatus "NetStream.Play.Start"
        sendOnStatus(ctx, streamId, "NetStream.Play.Start", "Playing started"); // 发送播放开始状态

        // 发送一些元数据
        sendStreamMetadata(ctx); // 发送流元数据

        state = RtmpState.STREAM_CREATED; // 更新状态为流已创建（实际为播放状态）
    }

    /**
     * 处理用户控制消息
     */
    private void handleUserControl(ChannelHandlerContext ctx, RtmpMessage msg) {
        if (msg.getPayload().length >= 6) {
            int eventType = (msg.getPayload()[0] << 8) | (msg.getPayload()[1] & 0xFF); // 获取事件类型
            System.out.println("User control event: " + eventType); // 用户控制事件
        }
    }

    /**
     * 处理窗口确认大小
     */
    private void handleWindowAckSize(ChannelHandlerContext ctx, RtmpMessage msg) {
        System.out.println("Window ACK size set"); // 窗口确认大小设置
    }

    /**
     * 处理块大小
     */
    private void handleChunkSize(ChannelHandlerContext ctx, RtmpMessage msg) {
        if (msg.getPayload().length >= 4) {
            int chunkSize = ((msg.getPayload()[0] & 0xFF) << 24) |
                            ((msg.getPayload()[1] & 0xFF) << 16) |
                            ((msg.getPayload()[2] & 0xFF) << 8) |
                            (msg.getPayload()[3] & 0xFF); // 计算块大小
            System.out.println("Chunk size set to: " + chunkSize); // 设置块大小
        }
    }

    /**
     * 发送连接结果
     */
    private void sendConnectResult(ChannelHandlerContext ctx, double transactionId) {
        // 创建响应载荷
        ByteBuf response = ctx.alloc().buffer(); // 分配响应缓冲区

        // 命令名称: "_result"
        writeAmfString(response, "_result"); // 写入命令名称

        // 事务 ID
        response.writeDouble(transactionId); // 写入事务 ID

        // 属性对象（目前为空）
        writeAmfObject(response, new HashMap<>()); // 写入空属性对象

        // 信息对象
        Map<String, Object> info = new HashMap<>(); // 创建信息对象
        info.put("fmsVer", "FMS/3,0,1,123"); // FMS 版本
        info.put("capabilities", 31.0); // 能力值
        writeAmfObject(response, info); // 写入信息对象

        // 发送响应
        RtmpMessage result = new RtmpMessage(3, 0, response.readableBytes(), // 创建响应消息
                                           (byte) 0x14, 0, getBytes(response)); // 消息类型为 AMF 命令
        ctx.writeAndFlush(result); // 写入并刷新
    }

    /**
     * 发送创建流结果
     */
    private void sendCreateStreamResult(ChannelHandlerContext ctx, double transactionId, int streamId) {
        // 创建响应载荷
        ByteBuf response = ctx.alloc().buffer(); // 分配响应缓冲区

        // 命令名称: "_result"
        writeAmfString(response, "_result"); // 写入命令名称

        // 事务 ID
        response.writeDouble(transactionId); // 写入事务 ID

        // 空值
        response.writeByte(0x05); // AMF 空值

        // 流 ID
        response.writeDouble(streamId); // 写入流 ID

        // 发送响应
        RtmpMessage result = new RtmpMessage(3, 0, response.readableBytes(), // 创建响应消息
                                           (byte) 0x14, 0, getBytes(response)); // 消息类型为 AMF 命令
        ctx.writeAndFlush(result); // 写入并刷新
    }

    /**
     * 发送状态消息
     */
    private void sendOnStatus(ChannelHandlerContext ctx, int streamId, String code, String description) {
        // 创建响应载荷
        ByteBuf response = ctx.alloc().buffer(); // 分配响应缓冲区

        // 命令名称: "onStatus"
        writeAmfString(response, "onStatus"); // 写入命令名称

        // 事务 ID: 0
        response.writeDouble(0.0); // 写入事务 ID

        // 空值
        response.writeByte(0x05); // AMF 空值

        // 信息对象
        Map<String, Object> info = new HashMap<>(); // 创建信息对象
        info.put("level", "status"); // 级别
        info.put("code", code); // 代码
        info.put("description", description); // 描述
        writeAmfObject(response, info); // 写入信息对象

        // 发送响应到流
        RtmpMessage result = new RtmpMessage(5, 0, response.readableBytes(), // 创建响应消息
                                           (byte) 0x14, streamId, getBytes(response)); // 消息类型为 AMF 命令
        ctx.writeAndFlush(result); // 写入并刷新
    }

    /**
     * 发送流元数据
     */
    private void sendStreamMetadata(ChannelHandlerContext ctx) {
        // 创建元数据载荷
        ByteBuf metadata = ctx.alloc().buffer(); // 分配元数据缓冲区

        // 命令名称: "@setDataFrame"
        writeAmfString(metadata, "@setDataFrame"); // 写入命令名称

        // 值: "onMetaData"
        writeAmfString(metadata, "onMetaData"); // 写入元数据名称

        // 元数据对象
        Map<String, Object> metadataObj = new HashMap<>(); // 创建元数据对象
        metadataObj.put("duration", 0.0); // 持续时间
        metadataObj.put("width", 640.0); // 宽度
        metadataObj.put("height", 480.0); // 高度
        metadataObj.put("videodatarate", 1000.0); // 视频码率
        metadataObj.put("framerate", 25.0); // 帧率
        metadataObj.put("videocodecid", 7.0); // 视频编码 ID (AVC)
        metadataObj.put("audiodatarate", 128.0); // 音频码率
        metadataObj.put("audiocodecid", 10.0); // 音频编码 ID (AAC)
        writeAmfObject(metadata, metadataObj); // 写入元数据对象

        // 发送元数据
        RtmpMessage meta = new RtmpMessage(5, 0, metadata.readableBytes(), // 创建元数据消息
                                         (byte) 0x12, streamId, getBytes(metadata)); // 消息类型为 AMF 数据
        ctx.writeAndFlush(meta); // 写入并刷新
    }

    /**
     * 读取 AMF 字符串
     */
    private String readAmfString(ByteBuf buffer) {
        if (buffer.readableBytes() < 2) return ""; // 缓冲区不足

        int length = buffer.readUnsignedShort(); // 读取长度
        if (buffer.readableBytes() < length) return ""; // 长度不足

        byte[] bytes = new byte[length]; // 创建字节数组
        buffer.readBytes(bytes); // 读取字节
        return new String(bytes, StandardCharsets.UTF_8); // 返回字符串
    }

    /**
     * 读取 AMF 对象
     */
    private Map<String, Object> readAmfObject(ByteBuf buffer) {
        Map<String, Object> obj = new HashMap<>(); // 创建对象映射

        // 这是一个简化实现 - 在真实系统中，您需要一个完整的 AMF 解析器
        while (buffer.isReadable()) {
            // 查找对象结束标记 (0x00, 0x00, 0x09)
            if (buffer.readableBytes() >= 3 &&
                buffer.getByte(buffer.readerIndex()) == 0x00 &&
                buffer.getByte(buffer.readerIndex() + 1) == 0x00 &&
                buffer.getByte(buffer.readerIndex() + 2) == 0x09) {
                buffer.skipBytes(3); // 跳过结束标记
                break;
            }

            // 读取键值对（简化）
            if (buffer.readableBytes() >= 2) {
                int keyLength = buffer.readUnsignedShort(); // 读取键长度
                if (buffer.readableBytes() >= keyLength + 1) {
                    byte[] keyBytes = new byte[keyLength]; // 创建键字节数组
                    buffer.readBytes(keyBytes); // 读取键
                    String key = new String(keyBytes, StandardCharsets.UTF_8); // 转换为字符串

                    byte type = buffer.readByte(); // 读取类型
                    Object value = null; // 初始化值

                    if (type == 0x02) { // 字符串类型
                        if (buffer.readableBytes() >= 2) {
                            int valueLength = buffer.readUnsignedShort(); // 读取值长度
                            if (buffer.readableBytes() >= valueLength) {
                                byte[] valueBytes = new byte[valueLength]; // 创建值字节数组
                                buffer.readBytes(valueBytes); // 读取值
                                value = new String(valueBytes, StandardCharsets.UTF_8); // 转换为字符串
                            }
                        }
                    } else if (type == 0x00) { // 数字类型
                        if (buffer.readableBytes() >= 8) {
                            value = buffer.readDouble(); // 读取双精度浮点数
                        }
                    } else if (type == 0x05) { // 空值类型
                        value = null; // 设置为空值
                    }

                    if (value != null) {
                        obj.put(key, value); // 添加到对象映射
                    }
                }
            }
        }

        return obj; // 返回对象映射
    }

    /**
     * 写入 AMF 字符串
     */
    private void writeAmfString(ByteBuf buffer, String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8); // 获取字节数组
        buffer.writeShort(bytes.length); // 写入长度
        buffer.writeBytes(bytes); // 写入字节
    }

    /**
     * 写入 AMF 对象
     */
    private void writeAmfObject(ByteBuf buffer, Map<String, Object> obj) {
        buffer.writeByte(0x03); // 对象类型

        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            // 写入键
            writeAmfString(buffer, entry.getKey()); // 写入键

            // 写入值
            Object value = entry.getValue();
            if (value instanceof String) {
                buffer.writeByte(0x02); // 字符串类型
                writeAmfString(buffer, (String) value); // 写入字符串值
            } else if (value instanceof Number) {
                buffer.writeByte(0x00); // 数字类型
                buffer.writeDouble(((Number) value).doubleValue()); // 写入数字值
            } else {
                buffer.writeByte(0x05); // 空值类型
            }
        }

        // 对象结束标记
        buffer.writeByte(0x00); // 写入结束标记字节 1
        buffer.writeByte(0x00); // 写入结束标记字节 2
        buffer.writeByte(0x09); // 写入结束标记字节 3
    }

    /**
     * 获取字节数组
     */
    private byte[] getBytes(ByteBuf buffer) {
        byte[] bytes = new byte[buffer.readableBytes()]; // 创建字节数组
        buffer.readBytes(bytes); // 读取所有字节
        return bytes; // 返回字节数组
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("Server handler error: " + cause.getMessage()); // 服务器处理器错误
        cause.printStackTrace(); // 打印异常堆栈
        ctx.close(); // 关闭连接
    }
}