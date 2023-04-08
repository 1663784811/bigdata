package com.cyyaw.service.sql.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.sql.CPageComponentsService;
import com.cyyaw.table.config.dao.CPageComponentsDao;
import com.cyyaw.table.config.entity.CPageComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CPageComponentsServiceImpl extends BaseService<CPageComponents, Integer> implements CPageComponentsService {

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return cPageComponentsDao;
    }
}
