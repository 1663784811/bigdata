package com.cyyaw.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.table.sql.entity.CSql;
import com.cyyaw.util.tools.CommonRest;

import java.util.List;
import java.util.Map;

public interface CommonService {


    /**
     * 通用查询
     */
    CommonRest query(JSONObject json);

    /**
     * 通用更新
     * @return
     */
    Map<String, Object> update(JSONObject json);

    /**
     * 通用删除
     */
    Map<String, Object> delete(JSONObject json);

    /**
     * 获取sql列表
     */
    List<CSql> sqlList();

    /**
     * 保存数据
     */
    CSql saveSql(CSql cSql);

}
