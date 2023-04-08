package com.cyyaw.service.sql.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.sql.CPageService;
import com.cyyaw.table.config.dao.CPageDao;
import com.cyyaw.table.config.entity.CPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CPageServiceImpl extends BaseService<CPage, Integer> implements CPageService {

    @Autowired
    private CPageDao cPageDao;

    @Override
    public BaseDao getBaseDao() {
        return cPageDao;
    }

}

