package com.cyyaw.store.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.store.service.GCartService;
import com.cyyaw.store.table.goods.dao.GCartDao;
import com.cyyaw.store.table.goods.entity.GCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GCartServiceImpl extends BaseService<GCart, Integer> implements GCartService {

    @Autowired
    private GCartDao gCarDao;

    @Override
    public BaseDao getBaseDao() {
        return gCarDao;
    }


}

