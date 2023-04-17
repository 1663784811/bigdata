package com.cyyaw.service.spider.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.spider.SpiderNickNameService;
import com.cyyaw.table.spider.spider.entity.SpiderNickName;
import com.cyyaw.table.spider.spider.entity.dao.SpiderNickNameDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class SpiderNickNameServiceImpl extends BaseService<SpiderNickName, Integer> implements SpiderNickNameService {

    @Autowired
    private SpiderNickNameDao spiderNickNameDao;

    @Override
    public BaseDao getBaseDao() {
        return spiderNickNameDao;
    }

}

