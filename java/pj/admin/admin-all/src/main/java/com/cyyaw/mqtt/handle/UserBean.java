package com.cyyaw.mqtt.handle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.websocket.Session;

@Data
public class UserBean {

    @JsonFormat
    private String userId;
    @JsonFormat
    private String avatar;
}
