package com.cyyaw.jpa.common.dao;

import com.alibaba.fastjson.JSONObject;
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
     *
     * @param json
     * @return
     */
    Map<String, Object> update(JSONObject json);

    /**
     * 通用删除
     *
     * @param json
     * @return
     */
    Map<String, Object> delete(JSONObject json);
}