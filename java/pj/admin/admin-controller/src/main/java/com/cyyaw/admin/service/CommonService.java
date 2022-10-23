package com.cyyaw.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.table.sql.entity.CSql;

import java.util.List;
import java.util.Map;

public interface CommonService {


    /**
     * 通用查询
     */
    Map<String, Object>  query(JSONObject json);

    /**
     * 通用更新
     * @return
     */
    Map<String, Object> update(JSONObject json);

    /**
     * 通用删除
     */
    Map<String, Object> delete(JSONObject json);

    List<CSql> sqlList();


    CSql updateSql(CSql cSql);

}
