package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.ACouponRangeService;
import com.cyyaw.config.table.table.dao.activity.ACouponRangeDao;
import com.cyyaw.config.table.table.entity.activity.ACouponRange;
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

