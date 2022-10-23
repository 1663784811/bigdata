package com.cyyaw.springboot;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Slf4j
@Configuration
@SpringBootApplication
public class SBApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SBApplication.class, args);

        //发布事件方式1
        context.publishEvent(new MyEvent("测试事件."));

    }


    /**
     * 爬取完成
     */
    @Async
    @EventListener(MyEvent.class)
    public void finish(MyEvent finish) {
        // 数据分析
        log.info(" -----------------{}", finish);

    }



}
