package com.cyyaw.testwebrtc.temp1.socket;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class TCPSocketClient extends TCPSocket {

    private static final String TAG = TCPSocketClient.class.getName();
    final private InetAddress address;
    final private int port;

    public TCPSocketClient(ExecutorService executor, TCPChannelEvents eventListener, InetAddress address, int port) {
        super(executor, eventListener);
        this.address = address;
        this.port = port;
    }

    /**
     * Connects to the peer.
     */
    @Nullable
    @Override
    public Socket connect() {
        Log.d(TAG, "Connecting to [" + address.getHostAddress() + "]:" + Integer.toString(port));

        try {
            return new Socket(address, port);
        } catch (IOException e) {
            reportError("Failed to connect: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isServer() {
        return false;
    }
}