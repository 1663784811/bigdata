package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TPhotoClassificationService;
import com.cyyaw.config.table.table.dao.tadmin.TPhotoClassificationDao;
import com.cyyaw.config.table.table.entity.tadmin.TPhotoClassification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TPhotoClassificationServiceImpl extends BaseService<TPhotoClassification, Integer> implements TPhotoClassificationService {

    @Autowired
    private TPhotoClassificationDao tPhotoClassificationDao;

    @Override
    public BaseDao getBaseDao() {
        return tPhotoClassificationDao;
    }

}

