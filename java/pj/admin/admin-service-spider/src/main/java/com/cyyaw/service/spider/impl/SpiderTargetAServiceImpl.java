package com.cyyaw.service.spider.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.spider.SpiderTargetAService;
import com.cyyaw.table.spider.spider.entity.SpiderTargetA;
import com.cyyaw.table.spider.spider.entity.dao.SpiderTargetADao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class SpiderTargetAServiceImpl extends BaseService<SpiderTargetA, Integer> implements SpiderTargetAService {

    @Autowired
    private SpiderTargetADao spiderTargetADao;

    @Override
    public BaseDao getBaseDao() {
        return spiderTargetADao;
    }

}

