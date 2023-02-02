package com.cyyaw.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.service.CInterfaceService;
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

