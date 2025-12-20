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

The server demonstrates the core NIO concepts and provides a solid foundation for building more complex network applications.
服务器演示了核心NIO概念，并为构建更复杂的网络应用程序提供了坚实的基础。