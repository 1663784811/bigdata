package com.cyyaw.admin.controller.common;

import com.cyyaw.table.sql.entity.CPage;
import com.cyyaw.table.sql.entity.CPageComponents;
import lombok.Data;

import java.util.List;

@Data
public class PageRest extends CPage {


    /**
     * 组件数据
     */
    List<CPageComponents> data;

}
