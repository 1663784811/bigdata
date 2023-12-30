package com.cyyaw.sql.table.dao;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.util.tools.BaseResult;

public interface CommonDao {

    /**
     * 通用查询
     *
     * @param json
     * @return
     */
    BaseResult<Object> query(JSONObject json);

    /**
     * 通用保存
     */
    BaseResult<Object> save(String code, JSONObject json);

    /**
     * 通用删除
     */
    BaseResult<Object> del(JSONObject json);

}
