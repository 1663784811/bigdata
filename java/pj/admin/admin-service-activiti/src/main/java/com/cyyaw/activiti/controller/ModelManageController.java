package com.cyyaw.activiti.controller;

import com.cyyaw.activiti.entity.ModelParam;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;


@RestController
@Api("工作流模型")
@RequestMapping("/admin/workflow")
public class ModelManageController {


    @Autowired
    RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;


    @ApiOperation("查询所有模型")
    @RequestMapping(value = "/modelLists", method = RequestMethod.POST)
    public BaseResult modelLists(@RequestParam(required = false) String key,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(defaultValue = "30") Integer pageSize,
                                 @RequestParam(defaultValue = "1") Integer pageNum
    ) {
        ModelQuery query = repositoryService.createModelQuery();
        if (StringUtils.isNotEmpty(key)) {
            query.modelKey(key);
        }
        if (StringUtils.isNotEmpty(name)) {
            query.modelName(name);
        }
        int start = (pageNum - 1) * pageSize;
        List<Model> data = query.orderByCreateTime().desc().listPage(start, pageSize);
        PageRespone respone = new PageRespone();
        respone.setContent(data);
        respone.setTotal((long) query.list().size());
        respone.setPage(pageNum);
        respone.setSize(pageSize);
        respone.setTotalPage(0);
        return BaseResult.ok(respone);
    }


    @ApiOperation("添加模型")
    @PostMapping("/add")
    public BaseResult addSave(@RequestBody ModelParam modelRequest) throws JsonProcessingException {
        Model model = repositoryService.newModel();
        model.setCategory(modelRequest.getCategory());
        model.setKey(modelRequest.getKey());
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put("name", modelRequest.getName());
        modelNode.put("description", modelRequest.getDescription());
        modelNode.put("revision", modelRequest.getVersion());
        model.setMetaInfo(modelNode.toString());
        model.setName(modelRequest.getName());
        model.setVersion(modelRequest.getVersion());
        ModelQuery modelQuery = repositoryService.createModelQuery();
        List<Model> list = modelQuery.modelKey(modelRequest.getKey()).list();
        if (list.size() > 0) {
            return BaseResult.fail("模型已经存在");
        } else {
            // 保存模型到act_re_model表
            repositoryService.saveModel(model);
            HashMap<String, Object> content = new HashMap();
            content.put("resourceId", model.getId());
            HashMap<String, String> properties = new HashMap();
            properties.put("process_id", modelRequest.getKey());
            properties.put("name", modelRequest.getName());
            properties.put("category", modelRequest.getCategory());
            content.put("properties", properties);
            HashMap<String, String> stencilset = new HashMap();
            stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            content.put("stencilset", stencilset);
            // 保存模型文件到act_ge_bytearray表
            repositoryService.addModelEditorSource(model.getId(), objectMapper.writeValueAsBytes(content));
            return BaseResult.ok();
        }

    }

    @ApiOperation("部署模型")
    @GetMapping("/deployModel")
    public BaseResult deployModel(String modelId) {
        try {
            Model model = repositoryService.getModel(modelId);
            byte[] modelData = repositoryService.getModelEditorSource(modelId);
            JsonNode jsonNode = objectMapper.readTree(modelData);
            BpmnModel bpmnModel = (new BpmnJsonConverter()).convertToBpmnModel(jsonNode);
            Deployment deploy = repositoryService.createDeployment().category(model.getCategory()).name(model.getName()).key(model.getKey()).addBpmnModel(model.getKey() + ".bpmn20.xml", bpmnModel).deploy();
            model.setDeploymentId(deploy.getId());
            repositoryService.saveModel(model);
            return BaseResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("流程图不合规范，请重新设计");
        }
    }

    @ApiOperation("删除模型")
    @GetMapping("/delModel")
    public BaseResult delModel(String modelId) {
        repositoryService.deleteModel(modelId);
        return BaseResult.ok("删除成功");
    }

    @ApiOperation("导出模型")
    @GetMapping("/exportModel")
    public void modelExport(@PathVariable String modelId, HttpServletResponse response) throws IOException {
        byte[] modelData = repositoryService.getModelEditorSource(modelId);
        JsonNode jsonNode = objectMapper.readTree(modelData);
        BpmnModel bpmnModel = (new BpmnJsonConverter()).convertToBpmnModel(jsonNode);
        byte[] xmlBytes = (new BpmnXMLConverter()).convertToXML(bpmnModel, "UTF-8");
        ByteArrayInputStream in = new ByteArrayInputStream(xmlBytes);
        IOUtils.copy(in, response.getOutputStream());
        String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setHeader("content-Type", "application/xml");
        response.flushBuffer();
    }



    @ApiOperation("打开在线编辑器时加载指定模型到页面")
    @GetMapping("/loadModel")
    public ObjectNode loadModel(String modelId) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectNode modelNode = null;
        Model model = repositoryService.getModel(modelId);
        if (model != null) {
            if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            } else {
                modelNode = objectMapper.createObjectNode();
                modelNode.put(ModelDataJsonConstants.MODEL_NAME, model.getName());
            }
            modelNode.put(ModelDataJsonConstants.MODEL_ID, model.getId());
            ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
            modelNode.put("model", editorJsonNode);
        }
        return modelNode;
    }


    @ApiOperation("保存流程图编辑器的信息")
    @PostMapping(value = "/model/save")
    public void saveModel(String modelId, String name, String description, String json_xml, String svg_xml) throws Exception {
        Model model = repositoryService.getModel(modelId);
        ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
        modelJson.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        model.setMetaInfo(modelJson.toString());
        model.setName(name);
        model.setDeploymentId(null);
        Integer version = model.getVersion();
        version++;
        model.setVersion(version);
        repositoryService.saveModel(model);
        repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));
        InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
//        TranscoderInput input = new TranscoderInput(svgStream);
//        PNGTranscoder transcoder = new PNGTranscoder();
//        // Setup output
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        TranscoderOutput output = new TranscoderOutput(outStream);
//        // Do the transformation
//        transcoder.transcode(input, output);
//        final byte[] result = outStream.toByteArray();
//        repositoryService.addModelEditorSourceExtra(model.getId(), result);
//        outStream.close();
    }

    /**
     * 获取流程图编辑器的汉化文件
     */
    @GetMapping("/editor/stencilset")
    public String getStencilset() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        return org.apache.commons.io.IOUtils.toString(stream, "utf-8");
    }

}
