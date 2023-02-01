package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TPowerService;
import com.cyyaw.config.table.table.dao.tadmin.TPowerDao;
import com.cyyaw.config.table.table.entity.tadmin.TPower;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TPowerServiceImpl extends BaseService<TPower, Integer> implements TPowerService {

    @Autowired
    private TPowerDao tPowerDao;

    @Override
    public BaseDao getBaseDao() {
        return tPowerDao;
    }

    @Override
    public List<TPower> findAdminPower(String tid) {
        return tPowerDao.findAdminPower(tid);
    }
}

