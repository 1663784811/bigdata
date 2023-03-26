package com.cyyaw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
public class AdminApplication {

    @Autowired
    @Qualifier("enterpriseDataSource")
    private DataSource dataSource;


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AdminApplication.class, args);
        log.info("------------ 启动成功 ---------");





//        LoginController bean = run.getBean(LoginController.class);
//        EnterpriseRegisterRequest b= new EnterpriseRegisterRequest();
//        EEnterprise e = new EEnterprise();
//        e.setCreateTime(new Date());
//        e.setCode("aaa");
//        e.setName("企业名称");
//        b.setEEnterprise(e);
//        LoginRequest l = new LoginRequest();
//        l.setCodeUuid("");
//        l.setCode("");
//        l.setUserName("root");
//        l.setPassword("root");
//        b.setAdmin(l);
//        bean.enterpriseRegister(b);


        AdminApplication bean = run.getBean(AdminApplication.class);
        bean.loadTable(bean.dataSource);







    }


    /**
     *
     * @param dataSource
     */
    public void loadTable(DataSource dataSource){
        System.out.println(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);





    }

}
