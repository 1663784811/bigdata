package com.cyyaw;

import com.cyyaw.entity.EnterpriseRegisterRequest;
import com.cyyaw.entity.LoginRequest;
import com.cyyaw.table.enterprise.entity.EEnterprise;
import com.cyyaw.tx.login.LoginController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class AdminApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AdminApplication.class, args);
        log.info("------------ 启动成功 ---------");
        LoginController bean = run.getBean(LoginController.class);

        EnterpriseRegisterRequest b= new EnterpriseRegisterRequest();
        EEnterprise e = new EEnterprise();
        e.setCreateTime(new Date());
        e.setCode("aaa");
        e.setName("企业名称");

        b.setEEnterprise(e);
        LoginRequest l = new LoginRequest();
        l.setCodeUuid("");
        l.setCode("");
        l.setUserName("root");
        l.setPassword("root");

        b.setAdmin(l);
        bean.enterpriseRegister(b);
    }

}
