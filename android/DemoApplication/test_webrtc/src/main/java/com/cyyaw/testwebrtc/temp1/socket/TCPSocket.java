package com.cyyaw.testwebrtc.temp1.socket;


import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

public abstract class TCPSocket extends Thread {

    private static final String TAG = TCPSocket.class.getName();

    private final ExecutorService executor;

    private final TCPChannelEvents eventListener;

    @Nullable
    private PrintWriter out;
    @Nullable
    private Socket rawSocket;

    @Nullable
    public abstract Socket connect();

    public abstract boolean isServer();

    TCPSocket(ExecutorService executor, TCPChannelEvents eventListener) {
        this.executor = executor;
        this.eventListener = eventListener;
    }

    @Override
    public void run() {
        Log.d(TAG, "Listening thread started...");
        // Receive connection to temporary variable first, so we don't block.
        Socket tempSocket = connect();
        BufferedReader in;
        Log.d(TAG, "TCP connection established.");
        if (rawSocket != null) {
            Log.e(TAG, "Socket already existed and will be replaced.");
        }
        rawSocket = tempSocket;
        // Connecting failed, error has already been reported, just exit.
        if (rawSocket != null) {
            try {
                out = new PrintWriter(new OutputStreamWriter(rawSocket.getOutputStream(), StandardCharsets.UTF_8), true);
                in = new BufferedReader(new InputStreamReader(rawSocket.getInputStream(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                reportError("Failed to open IO on rawSocket: " + e.getMessage());
                return;
            }
            Log.v(TAG, "Execute onTCPConnected");
            executor.execute(() -> {
                Log.v(TAG, "Run onTCPConnected");
                eventListener.onTCPConnected(isServer());
            });

            while (true) {
                String message;
                try {
                    // 读取发过来的数据
                    message = in.readLine();
                } catch (IOException e) {
                    break;
                }
                if (message != null) {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.v(TAG, "Receive: " + message);
                            eventListener.onTCPMessage(message);
                        }
                    });
                } else {
                    break;
                }
            }
        }
        disconnect();
    }

    public void disconnect() {
        try {
            if (rawSocket != null) {
                rawSocket.close();
                rawSocket = null;
                out = null;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        eventListener.onTCPClose();
                    }
                });
            }
        } catch (IOException e) {
            reportError("Failed to close rawSocket: " + e.getMessage());
        }
    }

    public void send(String message) {
        Log.v(TAG, "Send: " + message);
        if (out == null) {
            reportError("Sending data on closed socket.");
            return;
        }
        out.write(message + "\n");
        out.flush();
    }


    public void reportError(final String message) {
        Log.e(TAG, "TCP Error: " + message);
        if (!executor.isShutdown() && !executor.isTerminated()) {
            executor.execute(() -> {
                eventListener.onTCPError(message);
            });
        }
    }
}