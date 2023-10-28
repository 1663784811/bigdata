package com.cyyaw.sql.table.dao;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.jpa.util.entity.CommonSaveData;
import com.cyyaw.util.tools.CommonRest;

import java.util.List;
import java.util.Map;

public interface CommonDao {

    /**
     * 通用查询
     *
     * @param json
     * @return
     */
    CommonRest query(JSONObject json);

    /**
     * 通用查询
     */
    CommonRest query(String countSql, String querySql, JSONObject json, boolean touName);

    /**
     * 通用查询
     */
    List<JSONObject> query(String sqlcontent, JSONObject json, boolean touName);

    /**
     * 通用更新
     */
    Map<String, Object> save(CommonSaveData commonSaveData);

    /**
     * 通用删除
     *
     * @param json
     * @return
     */
    Map<String, Object> delete(JSONObject json);
}
