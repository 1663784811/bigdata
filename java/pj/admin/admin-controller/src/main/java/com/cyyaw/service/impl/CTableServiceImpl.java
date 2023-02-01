package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.CTableService;
import com.cyyaw.config.table.table.dao.config.CTableDao;
import com.cyyaw.config.table.table.entity.config.CTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CTableServiceImpl extends BaseService<CTable, Integer> implements CTableService {

    @Autowired
    private CTableDao cTableDao;

    @Override
    public BaseDao getBaseDao() {
        return cTableDao;
    }

}

