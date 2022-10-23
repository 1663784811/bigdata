package com.cyyaw.code.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.code.service.CTableService;
import com.cyyaw.code.table.entity.CTable;
import com.cyyaw.code.table.dao.CTableDao;

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

