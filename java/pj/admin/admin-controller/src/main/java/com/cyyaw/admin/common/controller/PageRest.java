package com.cyyaw.admin.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.table.sql.entity.CPage;
import lombok.Data;

@Data
public class PageRest extends CPage {


    /**
     * 组件数据
     */
    JSONObject data;

}
