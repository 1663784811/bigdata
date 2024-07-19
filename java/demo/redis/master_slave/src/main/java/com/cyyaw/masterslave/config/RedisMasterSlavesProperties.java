package com.cyyaw.masterslave.config;

import com.cyyaw.masterslave.bean.RedisMaster;
import com.cyyaw.masterslave.bean.RedisSlave;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 */
@ConfigurationProperties(prefix = "redis.ms")
@Data
public class RedisMasterSlavesProperties {
    // 主机
    private RedisMaster redisMaster;
    // 从机
    private List<RedisSlave> redisSlaves;
}
