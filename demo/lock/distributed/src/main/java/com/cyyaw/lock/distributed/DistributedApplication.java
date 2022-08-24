package com.cyyaw.lock.distributed;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DistributedApplication {


    public static void main(String[] args) {
        SpringApplication.run(DistributedApplication.class, args);
        log.info("启动完成");
    }


}
