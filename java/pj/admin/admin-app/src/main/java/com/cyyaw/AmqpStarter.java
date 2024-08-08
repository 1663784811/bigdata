package com.cyyaw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AmqpStarter implements ApplicationRunner {

    @Autowired
    private AmqpConfig amqpConfig;

    @Autowired
    private CustomBeanRegistrar customBeanRegistrar;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(" ============== 开始 rabbiMq 初始化: {}", "");
        amqpConfig.initializeAmqpComponents();
        log.info(" ==============  扫描包 : {}", "");
        customBeanRegistrar.scanAndRegisterBeans("com.cyyaw.mqtt.**");
    }

}
