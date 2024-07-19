package com.cyyaw.masterslave.bean;

import lombok.Data;

/**
 */
@Data
public class RedisMaster {
    private String host;
    private Integer port;
    private String password;
    // 其它参数...
}
