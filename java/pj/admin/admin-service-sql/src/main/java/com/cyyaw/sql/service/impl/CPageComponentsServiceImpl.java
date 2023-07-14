package com.cyyaw.sql.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.sql.service.CPageComponentsService;
import com.cyyaw.sql.table.dao.CPageComponentsDao;
import com.cyyaw.sql.table.entity.CPageComponents;
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
