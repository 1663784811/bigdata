package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.PPageService;
import com.cyyaw.config.table.table.dao.page.PPageDao;
import com.cyyaw.config.table.table.entity.page.PPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PPageServiceImpl extends BaseService<PPage, Integer> implements PPageService {

    @Autowired
    private PPageDao pPageDao;

    @Override
    public BaseDao getBaseDao() {
        return pPageDao;
    }

}

