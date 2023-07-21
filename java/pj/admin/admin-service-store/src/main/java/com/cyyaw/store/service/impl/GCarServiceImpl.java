package com.cyyaw.store.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.store.service.GCarService;
import com.cyyaw.store.table.goods.dao.GCarDao;
import com.cyyaw.store.table.goods.entity.GCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class GCarServiceImpl extends BaseService<GCar, Integer> implements GCarService {

    @Autowired
    private GCarDao gCarDao;

    @Override
    public BaseDao getBaseDao() {
        return gCarDao;
    }

}

