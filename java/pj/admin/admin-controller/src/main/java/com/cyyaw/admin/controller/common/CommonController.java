package com.cyyaw.admin.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.admin.service.CommonService;
import com.cyyaw.table.sql.entity.CSql;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 公共模块
 */
@RequestMapping("/home/common")
@RestController
public class CommonController {


    @Autowired
    private CommonService commonService;

    /**
     * 通用查询
     *
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }
        return commonService.query(json);
    }

    /**
     * 通用修改或添加
     */
    @RequestMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }
        return commonService.update(json);
    }

    /**
     * 通用删除
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }
        return commonService.delete(json);
    }


    @RequestMapping("/sqlList")
    public BaseResult sqlList() {
        List<CSql> data = commonService.sqlList();
        return BaseResult.ok(data);
    }

    @RequestMapping("/saveSql")
    public BaseResult saveSql(@RequestBody CSql cSql) {
        CSql data = commonService.saveSql(cSql);
        return BaseResult.ok(data);
    }

}
