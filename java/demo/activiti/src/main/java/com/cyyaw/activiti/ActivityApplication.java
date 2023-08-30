package com.cyyaw.activiti;


import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
public class ActivityApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ActivityApplication.class, args);
        log.info("======================     ok      =====================");
    }

}
