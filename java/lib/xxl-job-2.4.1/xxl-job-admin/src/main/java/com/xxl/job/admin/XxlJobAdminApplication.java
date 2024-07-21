package com.xxl.job.admin;

import groovy.util.logging.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@Slf4j
@SpringBootApplication
public class XxlJobAdminApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(XxlJobAdminApplication.class, args);
		Environment environment = run.getBean(Environment.class);
		System.out.println("打开程序：http://127.0.0.1:" + environment.getProperty("local.server.port")+"/xxl-job-admin");
	}

}