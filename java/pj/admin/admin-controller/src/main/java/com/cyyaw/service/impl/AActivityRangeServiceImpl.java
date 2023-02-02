package com.cyyaw.service.impl;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.AActivityRangeService;
import com.cyyaw.table.activity.AActivityRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AActivityRangeServiceImpl extends BaseService<AActivityRange, Integer> implements AActivityRangeService {

    @Autowired
    private AActivityRangeDao aActivityRangeDao;

    @Override
    public BaseDao getBaseDao() {
        return aActivityRangeDao;
    }

}

