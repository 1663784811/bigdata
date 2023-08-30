package com.cyyaw.activiti.config;


import org.activiti.engine.*;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {

    /**
     * 事务管理器
     */
    @Bean
    DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    /**
     * 工作流引擎配置
     */
    @Bean
    public SpringProcessEngineConfiguration engineConfiguration(DataSource dataSource, DataSourceTransactionManager manager) {
        SpringProcessEngineConfiguration processConfig = new SpringProcessEngineConfiguration();
        processConfig.setDataSource(dataSource);
        processConfig.setTransactionManager(manager);
        processConfig.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        return processConfig;
    }

    /**
     * 流程引擎
     */
    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(SpringProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean rest = new ProcessEngineFactoryBean();
        rest.setProcessEngineConfiguration(processEngineConfiguration);
        return rest;
    }


    @Bean
    public ProcessEngine processEngine(ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
        return processEngineFactoryBean.getObject();
    }

    /**
     * 资源服务service
     */
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    /**
     * 流程运行service
     */
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    /**
     * 任务管理service
     */
    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    /**
     * 历史管理service
     */
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

}
