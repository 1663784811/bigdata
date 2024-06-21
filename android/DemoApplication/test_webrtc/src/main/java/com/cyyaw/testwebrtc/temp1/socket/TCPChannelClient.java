package com.cyyaw.testwebrtc.temp1.socket;

import org.webrtc.ThreadUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;


public class TCPChannelClient {
    private static final String TAG = TCPChannelClient.class.getName();

    private final ThreadUtils.ThreadChecker executorThreadCheck;

    private TCPSocket socket;

    /**
     *
     */
    public TCPChannelClient(ExecutorService executor, TCPChannelEvents eventListener, String ip, int port) {
        executorThreadCheck = new ThreadUtils.ThreadChecker();
        executorThreadCheck.detachThread();
        InetAddress address;
        try {
            address = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            return;
        }
        if (address.isAnyLocalAddress()) {
            socket = new TCPSocketServer(executor, eventListener, address, port);
        } else {
            socket = new TCPSocketClient(executor, eventListener, address, port);
        }
        socket.start();
    }

    public void disconnect() {
        executorThreadCheck.checkIsOnValidThread();
        socket.disconnect();
    }

    /**
     * 发送消息
     */
    public void send(String message) {
        executorThreadCheck.checkIsOnValidThread();
        socket.send(message);
    }

}
