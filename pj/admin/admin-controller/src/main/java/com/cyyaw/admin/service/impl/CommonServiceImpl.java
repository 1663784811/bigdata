package com.cyyaw.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.admin.dao.CommonDao;
import com.cyyaw.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Map<String, Object> query(JSONObject json) {
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
}
