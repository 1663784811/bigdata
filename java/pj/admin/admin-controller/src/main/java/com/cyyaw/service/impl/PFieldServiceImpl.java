package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.PFieldService;
import com.cyyaw.config.table.table.dao.page.PFieldDao;
import com.cyyaw.config.table.table.entity.page.PField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PFieldServiceImpl extends BaseService<PField, Integer> implements PFieldService {

    @Autowired
    private PFieldDao pFieldDao;

    @Override
    public BaseDao getBaseDao() {
        return pFieldDao;
    }

}

