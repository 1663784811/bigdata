package com.cyyaw.test;


import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;


import java.util.List;

public class ActivityTest {


    @Test
    public void test01() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }


    @Test
    public void test02() {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://139.198.115.132:3306/activiti?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true")
                .setJdbcUsername("root")
                .setJdbcPassword("because")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .buildProcessEngine();
    }

    @Test
    public void test03() {
        System.out.println("==================  部署流程  ========================");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 关键
        DeploymentBuilder deployment = repositoryService.createDeployment();
        // =======================================================
        // 文件
        deployment.addClasspathResource("xxx/xxx.xml");
        deployment.name("第一个流程");
        // 字节流
        // deployment.addBytes();
        // 字符
        //deployment.addString();
        // 模型
        // deployment.addBpmnModel();
        // ========================================================
        deployment.deploy();
        System.out.println("==============================================");
    }


    @Test
    public void test04() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 关键
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        // 查询所有
        List<Deployment> list = deploymentQuery.list();
        System.out.println("==============================================");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId());
            System.out.println(list.get(i).getName());
        }

        System.out.println("==============================================");
    }


    @Test
    public void test05() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 关键
        runtimeService.startProcessInstanceById("");
        // runtimeService.startProcessInstanceByKey("");
        // runtimeService.startProcessInstanceByMessage()
    }

    @Test
    public void test06() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        // 关键
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskAssignee("");
        List<Task> list = taskQuery.list();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println(task.getName());
        }
    }


    @Test
    public void test07() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        // 关键
        taskService.complete("");
    }

}
