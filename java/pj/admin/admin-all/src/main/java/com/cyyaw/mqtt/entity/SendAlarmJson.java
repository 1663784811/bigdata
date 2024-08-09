package com.cyyaw.mqtt.entity;


import lombok.Data;

@Data
public class SendAlarmJson {

    /**
     * 类型 Sizeof(long) alarm
     */
    private String type;
    /**
     * 请求流水号 int防止异步错误，取值为当前毫秒级时间戳
     */
    private String txnNo;
    /**
     * 返回状态 Enumresult true/flase4 msg 失败原因 char[msg_LEN]
     */
    private String result;

}
