package com.cyyaw.mqtt.entity;


import lombok.Data;

import java.util.List;



@Data
public class LoginJson {

    /**
     * 系统版本号
     */
    private String softwareVersion;

    /**
     * 4G 协议版本
     */
    private String protocolVersion;

    /**
     * 模块在位数量
     */
    private String cirInPlaceNum;

    /**
     * 模块数据集
     */
    private List<CirSet> cirSet;


    @Data
    public static class CirSet {
        /**
         * 模块序号
         */
        private String cirNo;
        /**
         * 模块类型
         */
        private String cirType;
        /**
         * 模块容量
         */
        private String cirCapacity;
    }


}
