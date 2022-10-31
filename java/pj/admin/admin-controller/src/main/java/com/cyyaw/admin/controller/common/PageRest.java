package com.cyyaw.admin.controller.common;

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
