package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TMessageService;
import com.cyyaw.config.table.table.dao.tadmin.TMessageDao;
import com.cyyaw.config.table.table.entity.tadmin.TMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TMessageServiceImpl extends BaseService<TMessage, Integer> implements TMessageService {

    @Autowired
    private TMessageDao tMessageDao;

    @Override
    public BaseDao getBaseDao() {
        return tMessageDao;
    }

}

