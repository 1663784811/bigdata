package com.cyyaw.test;

import com.cyyaw.activiti.ActivitiEditorController;
import com.cyyaw.activiti.ActivityApplication;
import com.cyyaw.activiti.ModelParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ActivityApplication.class)
public class ActivitiEditorControllerTest {


    @Autowired
    private ActivitiEditorController activitiEditorController;


    @Test
    public void addModel() throws JsonProcessingException {
        ModelParam modelRequest = new ModelParam();
        modelRequest.setName("测试一");
        modelRequest.setKey("ssssss");
        modelRequest.setCategory("aaaa");
        modelRequest.setVersion(0);
        modelRequest.setDescription("ddddd");
        activitiEditorController.addModel(modelRequest);
    }


    @Test
    public void test01(){


//        activitiEditorController.getEditorJson()

    }


}
