package com.cyyaw.store.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.store.service.GDepositoryService;
import com.cyyaw.store.table.goods.dao.GDepositoryDao;
import com.cyyaw.store.table.goods.entity.GDepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class GDepositoryServiceImpl extends BaseService<GDepository, Integer> implements GDepositoryService {

    @Autowired
    private GDepositoryDao gDepositoryDao;

    @Override
    public BaseDao getBaseDao() {
        return gDepositoryDao;
    }

}

