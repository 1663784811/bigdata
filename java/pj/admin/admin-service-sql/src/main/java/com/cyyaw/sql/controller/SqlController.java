package com.cyyaw.sql.controller;

import cn.hutool.json.JSONObject;
import com.cyyaw.sql.service.SqlService;
import com.cyyaw.sql.table.entity.CSql;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(tags = "SQL配置模块")
@RequestMapping("/admin/common/sql")
@RestController
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @GetMapping("/sqlList")
    public BaseResult<CSql> sqlList(@RequestParam Map<String, Object> map) {
        return sqlService.sqlList(new JSONObject(map));
    }

    @PostMapping("/saveSql")
    public BaseResult saveSql(@RequestBody CSql cSql) {
        CSql save = sqlService.save(cSql);
        return BaseResult.ok(save);
    }

    @PostMapping("/delSql")
    public BaseResult delSql(@RequestBody Integer idArr[]) {
        sqlService.delSql(idArr);
        return BaseResult.ok("删除成功");
    }


}
