package com.cyyaw.service.impl;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.AActivityRangeTypeService;
import com.cyyaw.table.activity.AActivityRangeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AActivityRangeTypeServiceImpl extends BaseService<AActivityRangeType, Integer> implements AActivityRangeTypeService {

    @Autowired
    private AActivityRangeTypeDao aActivityRangeTypeDao;

    @Override
    public BaseDao getBaseDao() {
        return aActivityRangeTypeDao;
    }

}

