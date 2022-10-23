package com.cyyaw.code.service.impl;

import com.cyyaw.code.service.CComponentsService;
import com.cyyaw.code.table.dao.CComponentsDao;
import com.cyyaw.code.table.entity.CComponents;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CComponentsServiceImpl extends BaseService<CComponents, Integer> implements CComponentsService {

    @Autowired
    private CComponentsDao cComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return cComponentsDao;
    }

}

