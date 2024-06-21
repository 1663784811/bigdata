package com.cyyaw.testwebrtc.temp1.socket;


import android.util.Log;

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
     * Opens a listening socket and waits for a connection.
     */
    @Nullable
    @Override
    public Socket connect() {
        Log.d(TAG, "Listening on [" + address.getHostAddress() + "]:" + Integer.toString(port));

        final ServerSocket tempSocket;
        try {
            tempSocket = new ServerSocket(port, 0, address);
        } catch (IOException e) {
            reportError("Failed to create server socket: " + e.getMessage());
            return null;
        }

        synchronized (rawSocketLock) {
            if (serverSocket != null) {
                Log.e(TAG, "Server rawSocket was already listening and new will be opened.");
            }

            serverSocket = tempSocket;
        }

        try {
            return tempSocket.accept();
        } catch (IOException e) {
            reportError("Failed to receive connection: " + e.getMessage());
            return null;
        }
    }

    /**
     * Closes the listening socket and calls super.
     */
    @Override
    public void disconnect() {
        try {
            synchronized (rawSocketLock) {
                if (serverSocket != null) {
                    serverSocket.close();
                    serverSocket = null;
                }
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
