package com.cyyaw.lock.distributed;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

public class RedissonConfig {


    @Bean
    public Redisson redisson() {
        Config config = new Config();
        //单机版
        //config.useSingleServer().setAddress("redis://192.168.1.1:8001").setDatabase(0);

        //集群版
        config.useClusterServers()
                .addNodeAddress("redis://192.168.1.1:8001")
                .addNodeAddress("redis://192.168.1.1:8002")
                .addNodeAddress("redis://192.168.1.2:8001")
                .addNodeAddress("redis://192.168.1.2:8002")
                .addNodeAddress("redis://192.168.1.3:8001")
                .addNodeAddress("redis://192.168.1.3:8002");
        return (Redisson) Redisson.create(config);
    }


}
