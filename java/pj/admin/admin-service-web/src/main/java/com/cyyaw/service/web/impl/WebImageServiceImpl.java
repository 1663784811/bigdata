package com.cyyaw.service.web.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.web.WebImageService;
import com.cyyaw.table.web.dao.WebImageDao;
import com.cyyaw.table.web.entity.WebImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class WebImageServiceImpl extends BaseService<WebImage, Integer> implements WebImageService {

    @Autowired
    private WebImageDao webImageDao;

    @Override
    public BaseDao getBaseDao() {
        return webImageDao;
    }

    @Override
    public WebImage findByTid(String tid) {
        return webImageDao.findByTid(tid);
    }
}

