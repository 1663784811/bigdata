package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.PrMessageService;
import com.cyyaw.config.table.table.dao.PrMessageDao;
import com.cyyaw.config.table.table.entity.PrMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PrMessageServiceImpl extends BaseService<PrMessage, Integer> implements PrMessageService {

    @Autowired
    private PrMessageDao prMessageDao;

    @Override
    public BaseDao getBaseDao() {
        return prMessageDao;
    }

}

