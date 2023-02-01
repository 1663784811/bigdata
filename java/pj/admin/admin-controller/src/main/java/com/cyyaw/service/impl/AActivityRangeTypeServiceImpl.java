package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.AActivityRangeTypeService;
import com.cyyaw.config.table.table.dao.activity.AActivityRangeTypeDao;
import com.cyyaw.config.table.table.entity.activity.AActivityRangeType;
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

