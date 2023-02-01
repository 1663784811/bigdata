package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.CPageService;
import com.cyyaw.config.table.table.dao.config.CPageDao;
import com.cyyaw.config.table.table.entity.config.CPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CPageServiceImpl extends BaseService<CPage, Integer> implements CPageService {

    @Autowired
    private CPageDao cPageDao;

    @Override
    public BaseDao getBaseDao() {
        return cPageDao;
    }

}

