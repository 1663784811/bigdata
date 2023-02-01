package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.EStoreService;
import com.cyyaw.config.table.table.dao.enterprise.EStoreDao;
import com.cyyaw.config.table.table.entity.enterprise.EStore;
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

