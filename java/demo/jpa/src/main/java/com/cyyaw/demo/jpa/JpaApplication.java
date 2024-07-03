package com.cyyaw.demo.jpa;


import com.cyyaw.demo.jpa.service.CttContentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(JpaApplication.class, args);

        CttContentService bean = run.getBean(CttContentService.class);







    }


}
