package com.cyyaw.tx.admin.service.impl;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.table.admin.tadmin.dao.TPowerDao;
import com.cyyaw.table.admin.tadmin.entity.TPower;
import com.cyyaw.tx.admin.service.TPowerService;
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

