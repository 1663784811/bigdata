package com.cyyaw.mqtt.handle;


import cn.hutool.json.JSONObject;
import com.cyyaw.mqtt.MqttService;
import com.cyyaw.mqtt.MsgData;
import com.cyyaw.mqtt.MsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 聊天消息
 */
@Component
public class ChatMsgHandle implements MsgHandle {

    @Autowired
    private MqttService mqttService;

    @Override
    public String getHandleCode() {
        return MsgType.CHAT.getCode();
    }

    @Override
    public void handle(String from, String to, String data) {
        JSONObject entries = new JSONObject(data);


    }

}
