package com.cyyaw.service.admin.impl;


import com.cyyaw.service.admin.TPowerService;
import com.cyyaw.table.admin.dao.TPowerDao;
import com.cyyaw.table.admin.entity.TPower;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TPowerServiceImpl implements TPowerService {

    @Autowired
    private TPowerDao tPowerDao;

    @Override
    public List<TPower> findAdminPower(String tid) {
        return tPowerDao.findAdminPower(tid);
    }

    @Override
    public List<TPower> initPower(String enterpriseId) {



        return null;
    }
}

