package com.cyyaw.testwebrtc.temp1.socket;

public interface TCPChannelEvents {
    void onTCPConnected(boolean server);

    /**
     * 服务器消息
     */
    void onTCPMessage(String message);

    void onTCPError(String description);

    void onTCPClose();
}
