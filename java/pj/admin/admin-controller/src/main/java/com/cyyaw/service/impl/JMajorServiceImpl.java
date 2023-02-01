package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.JMajorService;
import com.cyyaw.config.table.table.dao.JMajorDao;
import com.cyyaw.config.table.table.entity.JMajor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class JMajorServiceImpl extends BaseService<JMajor, Integer> implements JMajorService {

    @Autowired
    private JMajorDao jMajorDao;

    @Override
    public BaseDao getBaseDao() {
        return jMajorDao;
    }

}

