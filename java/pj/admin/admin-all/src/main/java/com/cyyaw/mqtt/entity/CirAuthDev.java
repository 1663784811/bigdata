package com.cyyaw.mqtt.entity;


import com.sun.xml.bind.v2.model.core.ID;
import lombok.Data;

@Data
public class CirAuthDev {


    /**
     * 类型 Sizeof(long) cirAuth
     */
    private String type;
    /**
     * 请求流水号 int防止异步错误，取值为当前毫秒级时间戳
     */
    private String txnNo;
    /**
     * 平台编码 char[registerId_LEN] 运维监控系统设备ID
     */
    private String registerId;
    /**
     * 设备编码 char[deviceId_LEN] 唯一标识
     */
    private String deviceId;
    /**
     * 厂家编码 char[producer_LEN]
     */
    private String producer;
    /**
     * 站址 ID char[siteId_LEN]
     */
    private String siteId;
    /**
     * 模块 XX 数据集 Sizeof[CirCInfo_LEN]
     */
    private String cirSet;


    public static class CirSet {

        /**
         * 模块 XX 在位 char[cirNo_LEN]
         */
        private String cirNo;
        /**
         * 手动控制 char[cirManual_LEN] 0 断开，1 闭合
         */
        private String cirManual;
    }

}