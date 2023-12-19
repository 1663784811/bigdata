package com.cyyaw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootApplication
public class AllApplication {


    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(AllApplication.class, args);
        DataSource ds = run.getBean(DataSource.class);
        Connection connection = ds.getConnection();
        log.info("{}", connection.getCatalog());
        log.info("------------ 启动成功 ---------");
    }

}

