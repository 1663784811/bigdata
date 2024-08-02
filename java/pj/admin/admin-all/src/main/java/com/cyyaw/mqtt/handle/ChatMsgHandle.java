package com.cyyaw.mqtt.handle;


import cn.hutool.json.JSONObject;
import com.cyyaw.mqtt.MqttService;
import com.cyyaw.mqtt.MsgData;
import com.cyyaw.mqtt.MsgType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 聊天消息
 */
@Slf4j
@Component
public class ChatMsgHandle {

    @Autowired
    private MqttService mqttService;


    public void handle(String from, String to, String data) {
        MsgData msgData = new MsgData();
        msgData.setData(data);
        msgData.setFrom(from);
        msgData.setTo(to);
        mqttService.send(to, new JSONObject(msgData).toString());
    }


}
