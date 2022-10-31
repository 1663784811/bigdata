package com.cyyaw.admin.controller.common;

import com.cyyaw.table.sql.entity.CPage;
import lombok.Data;

import java.util.List;

@Data
public class PageRest extends CPage {


    /**
     * 组件数据
     */
    List<Object> data;

}
