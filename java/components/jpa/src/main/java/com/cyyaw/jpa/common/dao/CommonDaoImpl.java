package com.cyyaw.jpa.common.dao;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyyaw.util.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public CommonRest query(JSONObject json) {
        CommonRest rest = new CommonRest();
        //第一步：查询  sql 字符串
        String code = json.getString("code");
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from c_sql c where c.tid = ?", code);
        if (sqlRowSet.next()) {
            String countsql = sqlRowSet.getString("count_sql");
            String sqlcontent = sqlRowSet.getString("content_sql");
            return query(countsql, sqlcontent, json, false);
        } else {
            rest.setCode(-1);
            rest.setMsg("找不到可用条件");
        }
        return rest;
    }

    /**
     * @param sqlCount
     * @param sqlcontent
     * @param json
     * @return
     */
    public CommonRest query(String sqlCount, String sqlcontent, JSONObject json, boolean touName) {
        Integer page = json.getInteger("page");
        Integer size = json.getInteger("size");
        page = page == null ? 1 : page;
        size = size == null ? 30 : size;
        // ================
        String querySql = sqlcontent + " limit " + ((page - 1) * size + "," + size);
        String countSql = SqlUtils.explainSql(sqlCount, json);
        // 第二步：替换字符串
        log.info("统计sql语句: {} ", countSql);
        log.info("============================================");
        Integer total = jdbcTemplate.queryForObject(countSql, Integer.class);
        List<JSONObject> resData = query(querySql, json, touName);
        CommonRest rest = new CommonRest();
        rest.setCode(WebErrCodeEnum.WEB_SUCCESS.getCode());
        rest.setData(resData);
        rest.setMsg("ok");
        rest.setTotal(total);
        rest.setPage(page);
        rest.setSize(size);
        return rest;
    }

    /**
     * 通用查询
     */
    public List<JSONObject> query(String sqlContent, JSONObject json, boolean touName) {
        String querySql = SqlUtils.explainSql(sqlContent, json);
        log.info("执行sql语句: {} ", querySql);
        log.info("============================================");
        List<Map<String, Object>> data = jdbcTemplate.queryForList(querySql);
        List<JSONObject> resData = new ArrayList<>();
        if (null != data && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                Map<String, Object> mapObj = data.get(i);
                JSONObject jsObj = new JSONObject();
                for (String key : mapObj.keySet()) {
                    Object value = null;
                    Object dateTime = mapObj.get(key);
                    if (dateTime instanceof Timestamp) {
                        value = DateUtils.getStringDate((Timestamp) dateTime);
                    } else {
                        value = dateTime;
                    }
                    if (touName) {
                        jsObj.put(StrUtil.toCamelCase(key), value);
                    } else {
                        jsObj.put(key, value);
                    }
                }
                resData.add(jsObj);
            }
        }
        return resData;
    }


    @Override
    public Map<String, Object> update(JSONObject json) {
        // 第一步: 查询表结构
        String table = json.getString("table");
        JSONArray data = json.getJSONArray("data");
        JSONArray reArr = new JSONArray();
        if (null != data && data.size() > 0) {
            JSONArray page = tableInfo(table);
            JSONArray addArr = new JSONArray();
            JSONArray updateArr = new JSONArray();
            String pk = null;
            // 判断数据库是否有数据
            for (int i = 0; i < page.size(); i++) {
                JSONObject js = page.getJSONObject(i);
                String columnName = js.getString("column_name");
                String columnKey = js.getString("column_key");
                if (columnKey.equals("PRI")) {
                    pk = columnName;
                    for (int j = 0; j < data.size(); j++) {
                        JSONObject mm = data.getJSONObject(j);
                        String id = mm.getString(columnName);
                        if (WhyStringUtil.isEmpty(id)) {
                            addArr.add(mm);
                        } else {
                            updateArr.add(mm);
                        }
                    }
                    break;
                }
            }
            if (addArr.size() > 0) {
                for (int i = 0; i < addArr.size(); i++) {
                    List<String> list = new ArrayList<>();
                    StringBuffer datakey = new StringBuffer();
                    StringBuffer set = new StringBuffer();
                    JSONObject obj = addArr.getJSONObject(i);
                    int tree = 0;
                    int index = -1;
                    for (int j = 0; j < page.size(); j++) {
                        JSONObject js = page.getJSONObject(j);
                        String name = js.getString("column_name");
                        String dataType = js.getString("data_type");
                        String cn = obj.getString(name);
                        // 初始化数据
                        if (WhyStringUtil.isEmpty(cn) && name.equals("tid")) {
                            cn = WhyStringUtil.getUUID();
                        } else if (WhyStringUtil.isEmpty(cn) && name.equals("createtime")) {
                            cn = DateUtils.getStringDate(new Date());
                        } else if (WhyStringUtil.isEmpty(cn) && name.equals("del")) {
                            cn = "0";
                        }
                        if (
                                (!"datetime".equals(dataType) && null != cn)
                                        || "treecode".equals(name)
                                        || (!WhyStringUtil.isEmpty(cn))
                        ) {
                            if (datakey.length() > 0) {
                                datakey.append(",`" + name + "`");
                            } else {
                                datakey.append("`" + name + "`");
                            }
                            if (set.length() > 0) {
                                set.append(",?");
                            } else {
                                set.append("?");
                            }
                            if ("pid".equals(name) || "tid".equals(name) || "treecode".equals(name)) {
                                tree++;
                            }
                            if ("treecode".equals(name)) {
                                index = list.size();
                            }
                            list.add(cn);
                        }
                    }
                    if (tree == 3 && index != -1) {
                        String pid = obj.getString("pid");
                        if (null != pid) {
                            String parentTreeCode = "";
                            // 第二步： 查找新的父节点数据
                            List<Map<String, Object>> parentData = jdbcTemplate.queryForList("select * from " + table + " where `tid` = ?", pid);
                            if (parentData.size() > 0) {
                                Map<String, Object> parentObj = parentData.get(0);
                                parentTreeCode = parentObj.get("treecode").toString();
                            }
                            //获取新的 treecode
                            int l = parentTreeCode.length() + 3;
                            String max = "select ifnull(max(t.treecode),0)+1 as treecode from " + table + " t where length(t.treecode) = ? and t.treecode LIKE ? ";
                            List<Map<String, Object>> setcode = jdbcTemplate.queryForList(max, l, parentTreeCode + "%");
                            int trint = 1;
                            if (setcode.size() > 0) {
                                JSONObject js = JSONObject.parseObject(JSONObject.toJSONString(setcode.get(0)));
                                trint = js.getInteger("treecode");
                            }
                            String tr = WhyStringUtil.createStrLength(trint + "", 3, "0");
                            obj.put("treecode", parentTreeCode + tr);
                            list.set(index, parentTreeCode + tr);
                        }
                    }
                    String sqlinsert = "insert into " + table + "(" + datakey.toString() + ") values (" + set.toString() + ")";
                    jdbcTemplate.update(sqlinsert, list.toArray());
                }
            }
            // 修改
            if (updateArr.size() > 0) {
                if (!WhyStringUtil.isEmpty(pk)) {
                    for (int i = 0; i < updateArr.size(); i++) {
                        String pkvalue = null;
                        StringBuffer sb = new StringBuffer();
                        StringBuffer set = new StringBuffer();
                        JSONObject obj = updateArr.getJSONObject(i);
                        List<String> list = new ArrayList<>();
                        sb.append("update " + table + " set ");
                        int tree = 0;
                        for (int j = 0; j < page.size(); j++) {
                            JSONObject js = page.getJSONObject(j);
                            String name = js.getString("column_name");
                            String dataType = js.getString("data_type");
                            String columnKey = js.getString("column_key");
                            String cn = obj.getString(name);
                            if ((!"datetime".equals(dataType) && null != cn) || (!WhyStringUtil.isEmpty(cn))) {
                                if (columnKey.equals("PRI")) {
                                    pkvalue = cn;
                                } else {
                                    if (set.length() > 0) {
                                        set.append(",`" + name + "` = ? ");
                                    } else {
                                        set.append("`" + name + "` = ? ");
                                    }
                                    if ("pid".equals(name) || "tid".equals(name) || "treecode".equals(name)) {
                                        tree++;
                                    }
                                    list.add(cn);
                                }
                            }
                        }
                        sb.append(set);
                        sb.append(" where " + pk + " = ?");
                        if (null != pkvalue) {
                            list.add(pkvalue);
                            // 处理树
                            String oldTreecode = null;
                            String pid = obj.getString("pid");
                            if (tree == 3) {
                                // 第一步：查询原数据
                                String sql = "select * from " + table + " where `" + pk + "` = ?";
                                List<Map<String, Object>> oldData = jdbcTemplate.queryForList(sql, pkvalue);
                                Map<String, Object> old = oldData.get(0);
                                String oldPid = old.get("pid").toString();
                                // 判断是否修改了父节点
                                if (!pid.equals(oldPid)) {
                                    oldTreecode = old.get("treecode").toString();
                                    String parentTreeCode = "";
                                    // 第二步： 查找新的父节点数据
                                    List<Map<String, Object>> parentData = jdbcTemplate.queryForList("select * from " + table + " where `tid` = ?", pid);
                                    if (parentData.size() > 0) {
                                        Map<String, Object> parentObj = parentData.get(0);
                                        parentTreeCode = parentObj.get("treecode").toString();
                                    }
                                    //获取新的 treecode
                                    int l = parentTreeCode.length() + 3;
                                    String max = "select ifnull(max(t.treecode),0)+1 as treecode from " + table + " t where length(t.treecode) = ? and t.treecode LIKE ? ";
                                    List<Map<String, Object>> setcode = jdbcTemplate.queryForList(max, l, parentTreeCode + "%");
                                    int trint = 1;
                                    if (setcode.size() > 0) {
                                        JSONObject js = JSONObject.parseObject(JSONObject.toJSONString(setcode.get(0)));
                                        trint = js.getInteger("treecode");
                                    }
                                    String tr = WhyStringUtil.createStrLength(trint + "", 3, "0");
                                    obj.put("treecode", parentTreeCode + tr);
                                }
                            }
                            jdbcTemplate.update(sb.toString(), list.toArray());
                            if (oldTreecode != null) {
                                // 更新数据下的 treecode
                                String tr = obj.getString("treecode");
                                String updateTree = "update t_power t set t.treecode = concat( ? ,substring(t.treecode, ? )) where t.treecode like ? ";
                                jdbcTemplate.update(updateTree, tr, oldTreecode.length() + 1, oldTreecode + "%");
                            }
                        }
                    }
                }
            }
            reArr.addAll(addArr);
            reArr.addAll(updateArr);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "ok");
        map.put("data", reArr);
        return map;
    }

    @Override
    public Map<String, Object> delete(JSONObject json) {
        // 第一步: 查询表结构
        String table = json.getString("table");
        JSONArray data = json.getJSONArray("data");
        JSONArray reArr = new JSONArray();
        String pk = null;
        if (null != data && data.size() > 0) {
            JSONArray page = tableInfo(table);
            JSONArray delArr = new JSONArray();
            // 判断数据库是否有数据
            for (int i = 0; i < page.size(); i++) {
                JSONObject js = page.getJSONObject(i);
                String columnName = js.getString("column_name");
                String columnKey = js.getString("column_key");
                if (columnKey.equals("PRI")) {
                    pk = columnName;
                    for (int j = 0; j < data.size(); j++) {
                        JSONObject mm = data.getJSONObject(j);
                        String id = mm.getString(columnName);
                        if (!WhyStringUtil.isEmpty(id)) {
                            delArr.add(mm);
                        }
                    }
                    break;
                }
            }
            // 删除
            if (delArr.size() > 0) {
                List slist = new ArrayList<>();
                for (int i = 0; i < delArr.size(); i++) {
                    slist.add(delArr.getJSONObject(i).getString(pk));
                }
                String sql = "delete FROM " + table + " where " + pk + " in (" + WhyStringUtil.createStr(slist.size(), "?", ",") + ")";
                jdbcTemplate.update(sql, slist.toArray());
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "ok");
        map.put("data", reArr);
        return map;
    }


    private JSONArray tableInfo(String table) {
        StringBuffer sb = new StringBuffer("select ");
        sb.append(" table_name as table_name");
        sb.append(" ,column_name as column_name");
        sb.append(" ,data_type as data_type");
        sb.append(" ,column_key as column_key");
        sb.append(" from information_schema.columns where table_name= ?");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sb.toString(), table);
        return JSONArray.parseArray(JSON.toJSON(maps).toString());
    }
}
