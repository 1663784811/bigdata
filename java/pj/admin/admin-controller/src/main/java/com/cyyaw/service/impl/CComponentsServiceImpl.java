package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.CComponentsService;
import com.cyyaw.config.table.table.dao.config.CComponentsDao;
import com.cyyaw.config.table.table.entity.config.CComponents;
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

