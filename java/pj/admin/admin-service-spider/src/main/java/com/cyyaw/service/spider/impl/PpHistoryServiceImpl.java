package com.cyyaw.service.spider.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.spider.PpHistoryService;
import com.cyyaw.table.spider.user.dao.PpHistoryDao;
import com.cyyaw.table.spider.user.entity.PpHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class PpHistoryServiceImpl extends BaseService<PpHistory, Integer> implements PpHistoryService {

    @Autowired
    private PpHistoryDao ppHistoryDao;

    @Override
    public BaseDao getBaseDao() {
        return ppHistoryDao;
    }

}

