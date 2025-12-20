package com.cyyaw.nio;

import java.io.IOException;

/**
 * Main application class for the NIO server (NIO服务器的主应用程序类)
 * This class serves as the entry point for the NIO server application.
 * 该类是NIO服务器应用程序的入口点。
 */
public class NioApplication {

    /**
     * Main method to start the NIO server (启动NIO服务器的主方法)
     * @param args Command line arguments (命令行参数)
     */
    public static void main(String[] args) {
        try {
            // Create and start NIO server on port 18080 (在端口18080上创建并启动NIO服务器)
            NioServer server = new NioServer(18080);
            server.start();
        } catch (IOException e) {
            System.err.println("Failed to start NIO server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
