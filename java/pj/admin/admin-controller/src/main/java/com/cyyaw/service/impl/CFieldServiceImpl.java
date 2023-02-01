package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.CFieldService;
import com.cyyaw.config.table.table.dao.config.CFieldDao;
import com.cyyaw.config.table.table.entity.config.CField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CFieldServiceImpl extends BaseService<CField, Integer> implements CFieldService {

    @Autowired
    private CFieldDao cFieldDao;

    @Override
    public BaseDao getBaseDao() {
        return cFieldDao;
    }

}

