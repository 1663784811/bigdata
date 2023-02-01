package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.CInterfaceService;
import com.cyyaw.config.table.table.dao.config.CInterfaceDao;
import com.cyyaw.config.table.table.entity.config.CInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CInterfaceServiceImpl extends BaseService<CInterface, Integer> implements CInterfaceService {

    @Autowired
    private CInterfaceDao cInterfaceDao;

    @Override
    public BaseDao getBaseDao() {
        return cInterfaceDao;
    }

}

