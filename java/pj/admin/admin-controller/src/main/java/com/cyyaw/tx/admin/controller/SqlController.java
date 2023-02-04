package com.cyyaw.tx.admin.controller;

import com.cyyaw.entity.SelectEntity;
import com.cyyaw.table.confit.entity.CSql;
import com.cyyaw.tx.admin.service.SqlService;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/common/sql")
@RestController
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @GetMapping("/sqlList")
    public BaseResult<CSql> sqlList(SelectEntity select) {
        return sqlService.sqlList(select);
    }


}
