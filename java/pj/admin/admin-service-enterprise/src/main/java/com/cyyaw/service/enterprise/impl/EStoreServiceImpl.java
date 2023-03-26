package com.cyyaw.service.enterprise.impl;

import com.cyyaw.jpa.BaseDao;

import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.enterprise.EStoreService;
import com.cyyaw.table.enterprise.dao.EStoreDao;
import com.cyyaw.table.enterprise.entity.EStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EStoreServiceImpl extends BaseService<EStore, Integer> implements EStoreService {

    @Autowired
    private EStoreDao eStoreDao;

    @Override
    public BaseDao getBaseDao() {
        return eStoreDao;
    }

}

