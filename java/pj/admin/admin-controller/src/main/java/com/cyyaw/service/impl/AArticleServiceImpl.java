package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.AArticleService;
import com.cyyaw.config.table.table.dao.AArticleDao;
import com.cyyaw.config.table.table.entity.AArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AArticleServiceImpl extends BaseService<AArticle, Integer> implements AArticleService {

    @Autowired
    private AArticleDao aArticleDao;

    @Override
    public BaseDao getBaseDao() {
        return aArticleDao;
    }

}

