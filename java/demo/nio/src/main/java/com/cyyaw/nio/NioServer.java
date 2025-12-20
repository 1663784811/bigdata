package com.cyyaw.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO Server Implementation (NIO服务器实现)
 *
 * This class demonstrates the core concepts of Java NIO (Non-blocking I/O) programming.
 * This server uses Selector, ServerSocketChannel, and SocketChannel to efficiently handle
 * multiple client connections in a single-threaded model using the Reactor pattern.
 *
 * 该类演示了Java NIO（非阻塞I/O）编程的核心概念。
 * 该服务器使用Selector、ServerSocketChannel和SocketChannel通过Reactor模式在单线程模型中高效处理多个客户端连接。
 */
public class NioServer {
    // Selector: Multiplexes multiple channels for I/O operations (选择器：为I/O操作复用多个通道)
    private Selector selector;
    // ServerSocketChannel: Listens for incoming connections in non-blocking mode (服务器套接字通道：以非阻塞模式监听传入连接)
    private ServerSocketChannel serverChannel;
    private int port;
    // Volatile ensures visibility of changes across threads (volatile确保跨线程的变化可见性)
    private volatile boolean running = false;
    // Object for thread synchronization (用于线程同步的对象)
    private final Object lock = new Object();

    /**
     * Constructor to initialize the server with a specific port (用特定端口初始化服务器的构造函数)
     * @param port The port number to bind the server to (服务器绑定的端口号)
     */
    public NioServer(int port) {
        this.port = port;
    }

    /**
     * Start the NIO server (启动NIO服务器)
     *
     * This method initializes the selector, server socket channel, and starts the main event loop.
     * Key NIO concepts demonstrated:
     * - Selector: Monitors multiple channels for I/O events
     * - ServerSocketChannel: Handles server operations in non-blocking mode
     * - SelectionKey.OP_ACCEPT: Register for accept events
     *
     * 该方法初始化选择器、服务器套接字通道，并启动主事件循环。
     * 展示的关键NIO概念：
     * - Selector：监控多个通道的I/O事件
     * - ServerSocketChannel：以非阻塞模式处理服务器操作
     * - SelectionKey.OP_ACCEPT：注册接受事件
     */
    public void start() throws IOException {
        synchronized (lock) {
            if (running) {
                throw new IllegalStateException("Server is already running (服务器已在运行)");
            }

            // Create selector (创建选择器)
            selector = Selector.open();

            // Create server socket channel (创建服务器套接字通道)
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false); // Set non-blocking mode (设置非阻塞模式)

            // Allow port reuse to avoid "Address already in use" errors (允许端口复用以避免"地址已在使用"错误)
            serverChannel.setOption(java.net.StandardSocketOptions.SO_REUSEADDR, true);

            // Bind to port (绑定到端口)
            serverChannel.bind(new InetSocketAddress(port));
            System.out.println("NIO Server started on port: " + port);

            // Register the server channel with the selector for ACCEPT events (将服务器通道注册到选择器以接受事件)
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            running = true;
        }

        // Main event loop (主事件循环)
        while (running) {
            try {
                // Wait for events (this blocks until at least one event occurs) (等待事件发生（这会阻塞直到至少发生一个事件）)
                int readyChannels = selector.select(1000); // 1 second timeout (1秒超时)

                if (readyChannels == 0) {
                    // Check if server is still running during timeout (在超时期间检查服务器是否仍在运行)
                    continue;
                }

                // Get selected keys (获取选中的键)
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            // Handle new connection (处理新连接)
                            handleAccept(key);
                        } else if (key.isReadable()) {
                            // Handle data read (处理数据读取)
                            handleRead(key);
                        } else if (key.isWritable()) {
                            // Handle data write (处理数据写入)
                            handleWrite(key);
                        }
                    }

                    keyIterator.remove();
                }
            } catch (IOException e) {
                if (running) { // Only log error if server should still be running (仅当服务器应仍在运行时记录错误)
                    System.err.println("Error in selector loop: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (Exception e) {
                if (running) {
                    System.err.println("Unexpected error in selector loop: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        // Cleanup (清理)
        cleanup();
    }

    /**
     * Handle new client connections (处理新的客户端连接)
     *
     * This method is called when the selector detects an OP_ACCEPT event.
     * It accepts the new client connection and registers it with the selector for READ events.
     *
     * 当选择器检测到OP_ACCEPT事件时调用此方法。
     * 它接受新的客户端连接并将其注册到选择器以读取事件。
     */
    private void handleAccept(SelectionKey key) {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

        try {
            SocketChannel clientChannel = serverChannel.accept();

            if (clientChannel != null) {
                clientChannel.configureBlocking(false); // Set to non-blocking mode (设置为非阻塞模式)

                // Set socket options for better performance (设置套接字选项以获得更好的性能)
                clientChannel.setOption(java.net.StandardSocketOptions.SO_KEEPALIVE, true);
                clientChannel.setOption(java.net.StandardSocketOptions.TCP_NODELAY, true);

                // Register the client channel with the selector for READ events (将客户端通道注册到选择器以读取事件)
                SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);

                System.out.println("New client connected: " + clientChannel.getRemoteAddress());
            }
        } catch (IOException e) {
            System.err.println("Error accepting new client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handle data reading from client connections (处理从客户端连接读取数据)
     *
     * This method processes data received from clients and prepares an echo response.
     * It demonstrates ByteBuffer operations and the transition from READ to WRITE operations.
     *
     * 此方法处理从客户端接收到的数据并准备回声响应。
     * 它演示了ByteBuffer操作和从READ到WRITE操作的转换。
     */
    private void handleRead(SelectionKey key) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        // ByteBuffer: Manages data buffers for efficient I/O operations (字节缓冲区：为高效I/O操作管理数据缓冲区)
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            int bytesRead = clientChannel.read(buffer);

            if (bytesRead > 0) {
                // Flip the buffer to read mode (翻转缓冲区到读取模式)
                buffer.flip();

                // Convert buffer to string and print (将缓冲区转换为字符串并打印)
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                String message = new String(data).trim();

                System.out.println("Received from client " + getRemoteAddressSafely(clientChannel) + ": " + message);

                // Echo the message back to the client (将消息回声发送回客户端)
                String response = "Echo: Server received your message - " + message + "\n";
                ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());

                // Attach the response buffer to the key for later writing (将响应缓冲区附加到键以供稍后写入)
                key.attach(responseBuffer);
                // Change interest to WRITE operations (更改为WRITE操作)
                key.interestOps(SelectionKey.OP_WRITE);
            } else if (bytesRead == -1) {
                // Connection closed by client (客户端关闭连接)
                System.out.println("Client disconnected: " + getRemoteAddressSafely(clientChannel));
                cleanupClient(key, clientChannel);
            }
        } catch (IOException e) {
            System.err.println("Error reading from client " + getRemoteAddressSafely(clientChannel) + ": " + e.getMessage());
            cleanupClient(key, clientChannel);
        }
    }

    /**
     * Handle data writing to client connections (处理写入客户端连接的数据)
     *
     * This method sends responses to clients and switches back to READ operations after writing.
     * It demonstrates non-blocking write operations and buffer management.
     *
     * 此方法向客户端发送响应并在写入后切换回READ操作。
     * 它演示了非阻塞写操作和缓冲区管理。
     */
    private void handleWrite(SelectionKey key) {
        SocketChannel clientChannel = (SocketChannel) key.channel();

        try {
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            if (buffer != null) {
                int bytesWritten = clientChannel.write(buffer);

                if (!buffer.hasRemaining()) {
                    // Done writing, remove the attachment and change back to reading (写入完成，移除附件并切换回读取)
                    key.attach(null);
                    key.interestOps(SelectionKey.OP_READ);
                }
            } else {
                // No attachment, switch back to reading (没有附件，切换回读取)
                key.interestOps(SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            System.err.println("Error writing to client " + getRemoteAddressSafely(clientChannel) + ": " + e.getMessage());
            cleanupClient(key, clientChannel);
        }
    }

    /**
     * Safely get the remote address of a channel (安全地获取通道的远程地址)
     *
     * @param channel The SocketChannel to get address from (要获取地址的SocketChannel)
     * @return String representation of the remote address or "unknown" (远程地址的字符串表示或"unknown")
     */
    private String getRemoteAddressSafely(SocketChannel channel) {
        try {
            if (channel != null && channel.isOpen()) {
                java.net.SocketAddress address = channel.getRemoteAddress();
                return address != null ? address.toString() : "unknown";
            }
        } catch (IOException e) {
            // Ignore exception when trying to get remote address (尝试获取远程地址时忽略异常)
        }
        return "unknown";
    }

    /**
     * Clean up client connection resources (清理客户端连接资源)
     *
     * @param key The SelectionKey associated with the client (与客户端关联的选择键)
     * @param clientChannel The SocketChannel to close (要关闭的SocketChannel)
     */
    private void cleanupClient(SelectionKey key, SocketChannel clientChannel) {
        try {
            if (clientChannel != null) {
                clientChannel.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing client channel: " + e.getMessage());
        } finally {
            key.cancel(); // Remove the key from the selector (从选择器中移除键)
        }
    }

    /**
     * Stop the NIO server (停止NIO服务器)
     *
     * This method safely stops the server by setting the running flag to false.
     *
     * 此方法通过将运行标志设置为false来安全停止服务器。
     */
    public void stop() {
        synchronized (lock) {
            if (!running) {
                return; // Already stopped (已停止)
            }
            running = false;
        }
    }

    /**
     * Perform cleanup operations when shutting down (关闭时执行清理操作)
     *
     * This method closes all resources including the selector and server channel.
     *
     * 此方法关闭所有资源，包括选择器和服务器通道。
     */
    private void cleanup() {
        try {
            // Cancel all selection keys to wake up the selector (取消所有选择键以唤醒选择器)
            if (selector != null) {
                selector.keys().forEach(key -> {
                    try {
                        key.cancel();
                        if (key.channel() instanceof SocketChannel) {
                            ((SocketChannel) key.channel()).close();
                        }
                    } catch (IOException e) {
                        System.err.println("Error cancelling key: " + e.getMessage());
                    }
                });
            }

            if (serverChannel != null) {
                serverChannel.close();
            }

            if (selector != null) {
                selector.close();
            }

            System.out.println("NIO Server shut down cleanly");
        } catch (IOException e) {
            System.err.println("Error during cleanup: " + e.getMessage());
            e.printStackTrace();
        }
    }
}