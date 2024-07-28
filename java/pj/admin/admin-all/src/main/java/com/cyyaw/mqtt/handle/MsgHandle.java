package com.cyyaw.mqtt.handle;

public interface MsgHandle {

    String getHandleCode();


    void handle(String from, String to, String data);
}
