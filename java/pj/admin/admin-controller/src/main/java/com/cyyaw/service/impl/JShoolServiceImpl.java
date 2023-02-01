package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.JShoolService;
import com.cyyaw.config.table.table.dao.JShoolDao;
import com.cyyaw.config.table.table.entity.JShool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class JShoolServiceImpl extends BaseService<JShool, Integer> implements JShoolService {

    @Autowired
    private JShoolDao jShoolDao;

    @Override
    public BaseDao getBaseDao() {
        return jShoolDao;
    }

}

