package com.cyyaw.service.web.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.web.WebImageTypeService;
import com.cyyaw.table.web.dao.WebImageTypeDao;
import com.cyyaw.table.web.entity.WebImageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class WebImageTypeServiceImpl extends BaseService<WebImageType, Integer> implements WebImageTypeService {

    @Autowired
    private WebImageTypeDao webImageTypeDao;

    @Override
    public BaseDao getBaseDao() {
        return webImageTypeDao;
    }

}

