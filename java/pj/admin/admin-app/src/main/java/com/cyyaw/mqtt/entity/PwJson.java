package com.cyyaw.mqtt.entity;


import lombok.Data;

@Data
public class PwJson {

    /**
     *  返回状态
     */
    private String result;

    /**
     *  新账号
     */
    private String accountAck;


    /**
     *  新密码
     */
    private String pwdAck;


}
