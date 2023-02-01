package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TRolePowerService;
import com.cyyaw.config.table.table.dao.tadmin.TRolePowerDao;
import com.cyyaw.config.table.table.entity.tadmin.TRolePower;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TRolePowerServiceImpl extends BaseService<TRolePower, Integer> implements TRolePowerService {

    @Autowired
    private TRolePowerDao tRolePowerDao;

    @Override
    public BaseDao getBaseDao() {
        return tRolePowerDao;
    }

}

