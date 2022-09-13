package com.cyyaw.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;


@EntityScan(basePackages = {"com.cyyaw.table"})
@SpringBootApplication
public class BrowserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BrowserApplication.class, args);
    }
}
