package com.cyyaw.service.impl;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.AActivityDetailsService;
import com.cyyaw.table.store.activity.AActivityDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AActivityDetailsServiceImpl extends BaseService<AActivityDetails, Integer> implements AActivityDetailsService {

    @Autowired
    private AActivityDetailsDao aActivityDetailsDao;

    @Override
    public BaseDao getBaseDao() {
        return aActivityDetailsDao;
    }

}

