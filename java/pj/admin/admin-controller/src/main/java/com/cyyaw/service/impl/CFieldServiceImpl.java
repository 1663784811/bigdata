package com.cyyaw.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.table.confit.dao.CFieldDao;
import com.cyyaw.table.confit.entity.CField;
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

