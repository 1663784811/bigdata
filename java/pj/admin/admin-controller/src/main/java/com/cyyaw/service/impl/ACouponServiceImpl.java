package com.cyyaw.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.ACouponService;
import com.cyyaw.table.store.activity.ACoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ACouponServiceImpl extends BaseService<ACoupon, Integer> implements ACouponService {

    @Autowired
    private ACouponDao aCouponDao;

    @Override
    public BaseDao getBaseDao() {
        return aCouponDao;
    }

}

