package com.cyyaw.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    private String serverAddress;
    private int port;

    public NioClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void connectAndSend(String message) {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.configureBlocking(true); // For simplicity in this test client
            socketChannel.connect(new InetSocketAddress(serverAddress, port));

            // Send message
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            socketChannel.write(buffer);
            System.out.println("Sent message: " + message);

            // Read response
            buffer = ByteBuffer.allocate(1024);
            int bytesRead = socketChannel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip();
                byte[] response = new byte[buffer.remaining()];
                buffer.get(response);
                System.out.println("Received response: " + new String(response).trim());
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NioClient client = new NioClient("localhost", 8080);
        client.connectAndSend("Hello from NIO client!");
        client.connectAndSend("This is another test message.");
    }
}