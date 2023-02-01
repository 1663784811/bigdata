package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.PPageComponentsService;
import com.cyyaw.config.table.table.dao.page.PPageComponentsDao;
import com.cyyaw.config.table.table.entity.page.PPageComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PPageComponentsServiceImpl extends BaseService<PPageComponents, Integer> implements PPageComponentsService {

    @Autowired
    private PPageComponentsDao pPageComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return pPageComponentsDao;
    }

}

