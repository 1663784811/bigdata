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
        try {
            rawSocket = connect();
            out = new PrintWriter(new OutputStreamWriter(rawSocket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(rawSocket.getInputStream(), StandardCharsets.UTF_8));
            executor.execute(() -> {
                eventListener.onTCPConnected(isServer());
            });
            // 读取数据,没有数据了就关闭
            while (true) {
                // 读取发过来的数据
                String message = in.readLine();
                if (message != null) {
                    executor.execute(() -> {
                        eventListener.onTCPMessage(message);
                    });
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            Log.d(TAG, "ERROR...");
        }
        disconnect();
    }

    public void disconnect() {
        try {
            if (null != out) {
                out.close();
                out = null;
            }
            if (rawSocket != null) {
                rawSocket.close();
                rawSocket = null;
                executor.execute(() -> {
                    eventListener.onTCPClose();
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