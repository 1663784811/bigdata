package com.cyyaw;
import java.math.BigDecimal;
import java.util.Date;

import com.cyyaw.controller.login.LoginController;
import com.cyyaw.sql.controller.CPageController;
import com.cyyaw.user.controller.UUserController;
import com.cyyaw.user.table.entity.UUser;
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


//        UUserController bean = run.getBean(UUserController.class);
//        for (int i = 0; i <100; i++) {
//            UUser user = new UUser();
//            user.setDel(0);
//            user.setNote("");
//            user.setAccount("");
//            user.setPassword("");
//            user.setTrueName("naem"+i);
//            user.setPhone("");
//            user.setNickName("nickName"+i);
//            user.setFace("");
//            user.setSex("");
//            user.setCanLoginTime(new Date());
//            user.setEmail("");
//            user.setIp("");
//            user.setLastLoginTime(new Date());
//            user.setSalt("");
//            user.setStatus(0);
//            user.setType(0);
//            user.setAdminId("");
//            user.setBalance(new BigDecimal("0"));
//            user.setIntegral(0);
//            user.setOpenId("");
//            user.setUnionId("");
//            bean.saveUUser(user);
//        }

        run.getBean(CPageController.class);




    }

}






















