package com.cyyaw.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.AActivityService;
import com.cyyaw.table.store.activity.AActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AActivityServiceImpl extends BaseService<AActivity, Integer> implements AActivityService {

    @Autowired
    private AActivityDao aActivityDao;

    @Override
    public BaseDao getBaseDao() {
        return aActivityDao;
    }

}

