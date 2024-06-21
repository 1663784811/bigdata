package com.cyyaw.testwebrtc.temp1.socket;


import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 *
 */
public class TCPSocketServer extends TCPSocket {

    private static final String TAG = TCPSocketServer.class.getName();

    @Nullable
    private ServerSocket serverSocket;
    final private InetAddress address;
    final private int port;

    public TCPSocketServer(ExecutorService executor, TCPChannelEvents eventListener, InetAddress address, int port) {
        super(executor, eventListener);
        this.address = address;
        this.port = port;
    }

    /**
     */
    @Nullable
    @Override
    public Socket connect() {
        try {
            serverSocket = new ServerSocket(port, 0, address);
            return serverSocket.accept();
        } catch (IOException e) {
            reportError("Failed to create server socket: " + e.getMessage());
            return null;
        }
    }

    /**
     * Closes the listening socket and calls super.
     */
    @Override
    public void disconnect() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
        } catch (IOException e) {
            reportError("Failed to close server socket: " + e.getMessage());
        }
        super.disconnect();
    }

    @Override
    public boolean isServer() {
        return true;
    }
}
