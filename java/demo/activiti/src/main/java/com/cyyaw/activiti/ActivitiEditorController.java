package com.cyyaw.activiti;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/{eCode}/worker/model")
public class ActivitiEditorController {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping(value = "addModel")
    public void addModel(@RequestBody ModelParam modelRequest) throws JsonProcessingException {
        ModelQuery modelQuery = repositoryService.createModelQuery();
        List<Model> list = modelQuery.modelKey(modelRequest.getKey()).list();
        if (list.isEmpty()) {
            Model model = repositoryService.newModel();
            model.setCategory(modelRequest.getCategory());
            model.setKey(modelRequest.getKey());
            ObjectNode modelNode = objectMapper.createObjectNode();
            modelNode.put(ModelDataJsonConstants.MODEL_NAME, modelRequest.getName());
            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, modelRequest.getDescription());
            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, modelRequest.getVersion());
            model.setMetaInfo(modelNode.toString());
            model.setName(modelRequest.getName());
            model.setVersion(modelRequest.getVersion());
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
        }

    }


    @GetMapping(value = "json/{modelId}")
    public ObjectNode getEditorJson(@PathVariable String modelId) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectNode modelNode = null;
        Model model = repositoryService.getModel(modelId);
        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(ModelDataJsonConstants.MODEL_NAME, model.getName());
                }
                modelNode.put(ModelDataJsonConstants.MODEL_ID, model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
                modelNode.put("model", editorJsonNode);
            } catch (Exception e) {
                throw new ActivitiException("Error creating model JSON", e);
            }
        } else {
            model = repositoryService.newModel();
            model.setName("新的");


            ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree("");
            modelNode.put("model", editorJsonNode);
        }
        return modelNode;
    }


    /**
     * 保存流程图编辑器的信息
     *
     * @param modelId
     * @param name
     * @param description
     * @param json_xml
     * @param svg_xml
     */
    @RequestMapping(value = "/save/{modelId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, String name, String description, String json_xml, String svg_xml) {
        try {
            Model model = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            model.setDeploymentId(null);
            model.setVersion(model.getVersion()+1);
            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));
            repositoryService.addModelEditorSourceExtra(model.getId(), svg_xml.getBytes("utf-8"));
        } catch (Exception e) {
            throw new ActivitiException("Error saving model", e);
        }
    }


}
