package com.cyyaw.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.admin.dao.CommonDao;
import com.cyyaw.admin.service.CommonService;
import com.cyyaw.table.sql.dao.CSqlDao;
import com.cyyaw.table.sql.entity.CSql;
import com.cyyaw.util.tools.CommonRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private CSqlDao cSqlDao;


    @Override
    public CommonRest query(JSONObject json) {
        return commonDao.query(json);
    }

    @Override
    public Map<String, Object> update(JSONObject json) {
        return commonDao.update(json);
    }

    @Override
    public Map<String, Object> delete(JSONObject json) {
        return commonDao.delete(json);
    }

    @Override
    public List<CSql> sqlList() {
        return cSqlDao.findAll();
    }

    @Override
    public CSql saveSql(CSql cSql) {
        Integer id = cSql.getId();
        if (null != id) {
            CSql old = cSqlDao.findByid(id);
            BeanUtils.copyProperties(cSql, old);
            return cSqlDao.save(old);
        } else {
            return cSqlDao.save(cSql);
        }
    }
}
