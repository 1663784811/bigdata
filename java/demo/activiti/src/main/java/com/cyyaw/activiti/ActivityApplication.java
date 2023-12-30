package com.cyyaw.activiti;


import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
public class ActivityApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ActivityApplication.class, args);
        log.info("======================     ok      =====================");
        Environment environment = run.getBean(Environment.class);
        log.info("打开程序：http://127.0.0.1:" + environment.getProperty("local.server.port"));
    }

}
