package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TAdminMessageService;
import com.cyyaw.config.table.table.dao.tadmin.TAdminMessageDao;
import com.cyyaw.config.table.table.entity.tadmin.TAdminMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class TAdminMessageServiceImpl extends BaseService<TAdminMessage, Integer> implements TAdminMessageService {

    @Autowired
    private TAdminMessageDao tAdminMessageDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminMessageDao;
    }

}

