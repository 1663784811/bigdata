package com.cyyaw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EnableJpaRepositories(basePackages = {"com.cyyaw.table.spider.**"})
@SpringBootApplication
public class BrowserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BrowserApplication.class, args);
    }
}
