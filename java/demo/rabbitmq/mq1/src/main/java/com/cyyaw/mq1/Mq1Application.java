package com.cyyaw.mq1;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class Mq1Application {


    public static void main(String[] args) {

        SpringApplication.run(Mq1Application.class, args);

    }



}
