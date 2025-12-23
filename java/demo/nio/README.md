# NIO Server Implementation (NIO服务器实现)

This project demonstrates a complete NIO (Non-blocking I/O) server implementation in Java using the java.nio package.
本项目演示了使用java.nio包在Java中实现完整的NIO（非阻塞I/O）服务器。

## Features (功能特性)

- **Non-blocking I/O**: Uses Selector, ServerSocketChannel, and SocketChannel for efficient handling of multiple connections
  - **非阻塞I/O**: 使用Selector、ServerSocketChannel和SocketChannel来高效处理多个连接
- **Single-threaded model**: Handles multiple client connections with a single thread using the Reactor pattern
  - **单线程模型**: 使用Reactor模式通过单线程处理多个客户端连接
- **Proper resource management**: Clean handling of client connections and resource cleanup
  - **适当的资源管理**: 清洁地处理客户端连接和资源清理
- **Error handling**: Comprehensive error handling and logging
  - **错误处理**: 全面的错误处理和日志记录
- **Echo server**: Responds to client messages with an echo response
  - **回声服务器**: 用回声响应回复客户端消息

## Architecture (架构)

### Core Components (核心组件):

1. **NioServer.java**: Main server implementation
   - **NioServer.java**: 主服务器实现
   - Uses `Selector` to monitor multiple channels for I/O events
   - 使用`Selector`来监控多个通道的I/O事件
   - Handles `OP_ACCEPT`, `OP_READ`, and `OP_WRITE` events
   - 处理`OP_ACCEPT`、`OP_READ`和`OP_WRITE`事件
   - Manages client connections efficiently
   - 高效管理客户端连接

2. **NioApplication.java**: Entry point that starts the server
   - **NioApplication.java**: 启动服务器的入口点

3. **NioClient.java**: Test client to verify server functionality
   - **NioClient.java**: 验证服务器功能的测试客户端

### NIO Concepts Demonstrated (展示的NIO概念):

- **Selector**: Multiplexes multiple channels for I/O operations
  - **Selector（选择器）**: 为I/O操作复用多个通道
- **ServerSocketChannel**: Listens for incoming connections in non-blocking mode
  - **ServerSocketChannel（服务器套接字通道）**: 以非阻塞模式监听传入连接
- **SocketChannel**: Handles client connections in non-blocking mode
  - **SocketChannel（套接字通道）**: 以非阻塞模式处理客户端连接
- **SelectionKey**: Represents the registration of a channel with a selector
  - **SelectionKey（选择键）**: 表示通道与选择器的注册关系
- **ByteBuffer**: Manages data buffers for efficient I/O operations
  - **ByteBuffer（字节缓冲区）**: 为高效I/O操作管理数据缓冲区

## How to Run (如何运行)

### Compile (编译):
```bash
mkdir -p target/classes
javac -d target/classes src/main/java/com/cyyaw/nio/*.java
```

### Run the Server (运行服务器):
```bash
java -cp target/classes com.cyyaw.nio.NioApplication
```

### Run the Client (in another terminal) (运行客户端（在另一个终端）):
```bash
java -cp target/classes com.cyyaw.nio.NioClient
```

## Key NIO Operations (关键NIO操作)

1. **Accept**: Server accepts new client connections
   - **Accept（接受）**: 服务器接受新的客户端连接
2. **Read**: Server reads data from client connections
   - **Read（读取）**: 服务器从客户端连接读取数据
3. **Write**: Server writes responses back to clients
   - **Write（写入）**: 服务器将响应写回客户端
4. **Connection Management**: Handles client connect/disconnect events
   - **连接管理**: 处理客户端连接/断开事件

## Benefits of NIO (NIO的优势)

- **Scalability**: Can handle thousands of concurrent connections efficiently
  - **可扩展性**: 可以高效处理数千个并发连接
- **Resource Efficiency**: Single thread can manage multiple connections
  - **资源效率**: 单线程可以管理多个连接
- **Performance**: Avoids the overhead of creating a thread per connection
  - **性能**: 避免为每个连接创建线程的开销
- **Non-blocking**: I/O operations don't block the thread when no data is available
  - **非阻塞**: 当没有数据可用时，I/O操作不会阻塞线程

## RTSP Server Extension (RTSP服务器扩展)

This project also includes an RTSP (Real Time Streaming Protocol) server implementation using Netty framework.
本项目还包括使用Netty框架实现的RTSP（实时流协议）服务器。

### RTSP Features:
- **RTSP Protocol Support**: Complete implementation of RTSP server using Netty
  - **RTSP协议支持**: 使用Netty完成RTSP服务器的实现
- **Standard Methods**: Supports OPTIONS, DESCRIBE, SETUP, PLAY, and TEARDOWN methods
  - **标准方法**: 支持OPTIONS、DESCRIBE、SETUP、PLAY和TEARDOWN方法
- **SDP Support**: Generates Session Description Protocol responses
  - **SDP支持**: 生成会话描述协议响应
- **Session Management**: Handles RTSP session state and transport parameters
  - **会话管理**: 处理RTSP会话状态和传输参数

### How to Run RTSP Server:
Prerequisites: Maven must be installed for dependency management.
前置条件：必须安装Maven进行依赖管理。

```bash
# Build with Maven (使用Maven构建)
mvn clean compile

# Run the RTSP Server via NIO Application (通过NIO应用程序运行RTSP服务器)
mvn exec:java -Dexec.mainClass="com.cyyaw.nio.NioApplication" -Dexec.args="rtsp"

# Or run the RTSP Server via dedicated Netty Application (或通过专用的Netty应用程序运行RTSP服务器)
mvn exec:java -Dexec.mainClass="com.cyyaw.netty.NettyApplication"

# Or specify a custom port (或指定自定义端口)
mvn exec:java -Dexec.mainClass="com.cyyaw.netty.NettyApplication" -Dexec.args="8556"
```

The server demonstrates both core NIO concepts and modern Netty framework usage, providing a solid foundation for building more complex network applications.
服务器演示了核心NIO概念和现代Netty框架的使用，为构建更复杂的网络应用程序提供了坚实的基础。