package com.cyyaw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class AllApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AllApplication.class, args);
        log.info("------------ 启动成功 ---------");


//        LoginController bean = run.getBean(LoginController.class);
//        EnterpriseRegisterRequest request = new EnterpriseRegisterRequest();
//        EEnterprise eEnterprise = new EEnterprise();
//        eEnterprise.setTid("");
//        eEnterprise.setCreateTime(new Date());
//        eEnterprise.setDel(0);
//        eEnterprise.setNote("");
//        eEnterprise.setCode("aaa");
//        eEnterprise.setName("aaa");
//        eEnterprise.setUrl("aaa");
//        eEnterprise.setLogo("aaa");
//        request.setEEnterprise(eEnterprise);
//        LoginRequest admin = new LoginRequest();
//        admin.setCodeUuid("");
//        admin.setCode("aaa");
//        admin.setEnterpriseId("");
//        admin.setUserName("aaa");
//        admin.setPassword("aaa");
//        request.setAdmin(admin);
//        bean.enterpriseRegister(request);


    }

}






















