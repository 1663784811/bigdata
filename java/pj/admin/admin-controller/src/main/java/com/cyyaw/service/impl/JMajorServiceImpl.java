package com.cyyaw.service.impl;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.service.JMajorService;
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

