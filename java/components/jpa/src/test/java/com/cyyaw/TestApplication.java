package com.cyyaw;

import com.cyyaw.jpa.common.dao.CommonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Slf4j
@EnableSwagger2
@SpringBootApplication
public class TestApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TestApplication.class, args);
        log.info("------------ 启动成功 ---------");

        CommonDao bean = run.getBean(CommonDao.class);



//        bean.save();




    }

}
