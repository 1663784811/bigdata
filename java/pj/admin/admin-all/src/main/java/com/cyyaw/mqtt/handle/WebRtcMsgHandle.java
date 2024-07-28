package com.cyyaw.mqtt.handle;


import com.cyyaw.mqtt.MsgType;
import org.springframework.stereotype.Component;

@Component
public class WebRtcMsgHandle implements MsgHandle {


    @Override
    public String getHandleCode() {
        return MsgType.WEBRTC.getCode();
    }

    @Override
    public void handle(String from, String to, String data) {


        System.out.println("==========" + from + to + data);
    }


}
