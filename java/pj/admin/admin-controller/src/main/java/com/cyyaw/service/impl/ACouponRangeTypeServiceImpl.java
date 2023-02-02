package com.cyyaw.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.ACouponRangeTypeService;
import com.cyyaw.table.store.activity.ACouponRangeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ACouponRangeTypeServiceImpl extends BaseService<ACouponRangeType, Integer> implements ACouponRangeTypeService {

    @Autowired
    private ACouponRangeTypeDao aCouponRangeTypeDao;

    @Override
    public BaseDao getBaseDao() {
        return aCouponRangeTypeDao;
    }

}

