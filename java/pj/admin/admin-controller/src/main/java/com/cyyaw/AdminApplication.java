package com.cyyaw;


import com.cyyaw.init.InitApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class AdminApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AdminApplication.class, args);
        log.info("------------ 启动成功 ---------");

        InitApplication bean = run.getBean(InitApplication.class);
        bean.init();
    }

}
