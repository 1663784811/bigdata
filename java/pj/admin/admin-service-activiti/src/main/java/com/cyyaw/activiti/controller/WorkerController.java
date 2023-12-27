package com.cyyaw.activiti.controller;


import com.cyyaw.activiti.service.WorkerService;
import com.cyyaw.activiti.table.dto.ModelParam;
import com.cyyaw.util.tools.BaseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Api(tags = "工作流-流程维护")
@RestController
@RequestMapping("/admin/{eCode}/worker/mode")
public class WorkerController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WorkerService workerService;


    @ApiOperation(value = "查询所有模型")
    @GetMapping("queryModeList")
    public BaseResult queryModeList(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "30") Integer pageSize,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum
    ) {
        return workerService.queryModeList(key, name, pageSize, pageNum);
    }

    @ApiOperation(value = "查询所有模型")
    @RequestMapping("/queryModelById")
    public BaseResult<Object> queryModelById(String modelId) throws IOException {
        return workerService.queryModelById(modelId);
    }

    @GetMapping("/exportModel")
    public void exportModel(@PathVariable String modelId, HttpServletResponse response) throws IOException {
        byte[] modelData = repositoryService.getModelEditorSource(modelId);
        JsonNode jsonNode = objectMapper.readTree(modelData);
        BpmnModel bpmnModel = (new BpmnJsonConverter()).convertToBpmnModel(jsonNode);
        byte[] xmlBytes = (new BpmnXMLConverter()).convertToXML(bpmnModel, "UTF-8");
        ByteArrayInputStream in = new ByteArrayInputStream(xmlBytes);
        IOUtils.copy(in, response.getOutputStream());
        String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
        response.setHeader("Content-Disposition","attachment;filename=" + filename);
        response.setHeader("content-Type", "application/xml");
        response.flushBuffer();
    }

    @ApiOperation(value = "添加模型")
    @PostMapping("addModel")
    public BaseResult<Object> addModel(@RequestBody ModelParam modelRequest) throws JsonProcessingException {
        return workerService.addModel(modelRequest);
    }

    @ApiOperation(value = "删除模型")
    @PostMapping("delModel")
    public BaseResult<Object> delModel(String modelId) throws JsonProcessingException {
        return workerService.delModel(modelId);
    }


}
