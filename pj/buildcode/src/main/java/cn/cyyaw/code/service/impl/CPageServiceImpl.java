package com.cyyaw.code.service.impl;

import com.cyyaw.code.service.CPageService;
import com.cyyaw.code.table.dao.CPageDao;
import com.cyyaw.code.table.entity.CPage;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
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

