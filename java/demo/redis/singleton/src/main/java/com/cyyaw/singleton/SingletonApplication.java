package com.cyyaw.singleton;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SingletonApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SingletonApplication.class, args);


    }


}
