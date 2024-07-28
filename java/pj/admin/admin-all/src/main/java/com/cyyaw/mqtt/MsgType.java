package com.cyyaw.mqtt;


/**
 * 消息数据
 */

public enum MsgType {

    WEBRTC("webrtc", "webrtc"), CHAT("chat", "聊天数据");
    private String code;
    private String note;

    MsgType(String code, String note) {
        this.code = code;
        this.note = note;
    }


    public String getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }

}
