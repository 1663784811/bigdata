package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TAdminPowerService;
import com.cyyaw.config.table.table.dao.tadmin.TAdminPowerDao;
import com.cyyaw.config.table.table.entity.tadmin.TAdminPower;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TAdminPowerServiceImpl extends BaseService<TAdminPower, Integer> implements TAdminPowerService {

    @Autowired
    private TAdminPowerDao tAdminPowerDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminPowerDao;
    }

}

