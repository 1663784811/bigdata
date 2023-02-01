package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TAdminService;
import com.cyyaw.config.table.table.dao.tadmin.TAdminDao;
import com.cyyaw.config.table.table.entity.tadmin.TAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TAdminServiceImpl extends BaseService<TAdmin, Integer> implements TAdminService {

    @Autowired
    private TAdminDao tAdminDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminDao;
    }


    @Override
    public TAdmin findByAccount(String name) {
        return  tAdminDao.findByAccount(name);
    }
}

