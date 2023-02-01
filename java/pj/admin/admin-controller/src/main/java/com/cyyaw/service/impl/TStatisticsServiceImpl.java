package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TStatisticsService;
import com.cyyaw.config.table.table.dao.tadmin.TStatisticsDao;
import com.cyyaw.config.table.table.entity.tadmin.TStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TStatisticsServiceImpl extends BaseService<TStatistics, Integer> implements TStatisticsService {

    @Autowired
    private TStatisticsDao tStatisticsDao;

    @Override
    public BaseDao getBaseDao() {
        return tStatisticsDao;
    }

}

