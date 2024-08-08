package com.cyyaw.mqtt.entity;


import lombok.Data;


/**
 * B.2.1 申请账号
 * 硬件发布：get_dev_pwd/厂家编码/设备编码。
 * 硬件订阅：topic_json_pwd/厂家编码/设备编码。
 */
@Data
public class PwDev {

    /**
     * 初始账号
     */
    private String account;

    /**
     * 初始密码
     */
    private String pwd;


}
