package com.cyyaw.masterslave;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@EnableCaching
@SpringBootApplication
public class MasterSlaveApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MasterSlaveApplication.class, args);


    }


}
