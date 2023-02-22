package com.cyyaw.jpa.util.entity;


import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class CommonSaveData {

    private String table;

    private JSONArray data;

}
