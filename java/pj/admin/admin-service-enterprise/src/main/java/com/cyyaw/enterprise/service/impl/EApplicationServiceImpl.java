package com.cyyaw.enterprise.service.impl;

import com.cyyaw.enterprise.service.EApplicationService;
import com.cyyaw.enterprise.table.dao.EApplicationDao;
import com.cyyaw.enterprise.table.entity.EApplication;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class EApplicationServiceImpl extends BaseService<EApplication, Integer> implements EApplicationService {

    @Autowired
    private EApplicationDao eApplicationDao;

    @Override
    public BaseDao getBaseDao() {
        return eApplicationDao;
    }

    @Override
    public EApplication findByCode(String appId) {
        EApplication eApplication = eApplicationDao.findByCode(appId);
        return eApplication;
    }
}

