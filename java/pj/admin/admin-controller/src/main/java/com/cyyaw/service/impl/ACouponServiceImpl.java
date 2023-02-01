package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.ACouponService;
import com.cyyaw.config.table.table.dao.activity.ACouponDao;
import com.cyyaw.config.table.table.entity.activity.ACoupon;
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

