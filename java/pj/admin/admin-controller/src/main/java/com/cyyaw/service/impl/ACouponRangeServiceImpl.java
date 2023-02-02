package com.cyyaw.service.impl;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.ACouponRangeService;
import com.cyyaw.table.activity.ACouponRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ACouponRangeServiceImpl extends BaseService<ACouponRange, Integer> implements ACouponRangeService {

    @Autowired
    private ACouponRangeDao aCouponRangeDao;

    @Override
    public BaseDao getBaseDao() {
        return aCouponRangeDao;
    }

}

