package com.cyyaw.activiti.entity;

import lombok.Data;

@Data
public class ModelRequest {

    private String modelId;
    private String name;
    private String description;
    private String json_xml;
    private String svg_xml;

}
