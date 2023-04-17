package com.cyyaw.service.spider.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.spider.PpPeopleService;
import com.cyyaw.table.spider.user.dao.PpPeopleDao;
import com.cyyaw.table.spider.user.entity.PpPeople;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class PpPeopleServiceImpl extends BaseService<PpPeople, Integer> implements PpPeopleService {

    @Autowired
    private PpPeopleDao ppPeopleDao;

    @Override
    public BaseDao getBaseDao() {
        return ppPeopleDao;
    }

}

