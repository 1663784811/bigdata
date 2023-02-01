package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.PrUserService;
import com.cyyaw.config.table.table.dao.PrUserDao;
import com.cyyaw.config.table.table.entity.PrUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PrUserServiceImpl extends BaseService<PrUser, Integer> implements PrUserService {

    @Autowired
    private PrUserDao prUserDao;

    @Override
    public BaseDao getBaseDao() {
        return prUserDao;
    }

}

