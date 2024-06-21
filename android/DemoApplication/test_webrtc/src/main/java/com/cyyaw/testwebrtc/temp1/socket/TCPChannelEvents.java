package com.cyyaw.testwebrtc.temp1.socket;

public interface TCPChannelEvents {
    void onTCPConnected(boolean server);

    void onTCPMessage(String message);

    void onTCPError(String description);

    void onTCPClose();
}
