package com.cyyaw.service.sql.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.service.sql.CPageComponentsService;
import com.cyyaw.service.sql.CPageService;
import com.cyyaw.table.config.dao.CPageDao;
import com.cyyaw.table.config.entity.CPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CPageServiceImpl extends BaseService<CPage, Integer> implements CPageService {

    @Autowired
    private CPageDao cPageDao;

    @Autowired
    private CPageComponentsService cPageComponentsService;

    @Override
    public BaseDao getBaseDao() {
        return cPageDao;
    }

    @Override
    public CPage findByTid(String tid) {
        CPage cPage = new CPage();
        cPage.setTid(tid);
        Example<CPage> example = Example.of(cPage);
        List<CPage> all = cPageDao.findAll(example);
        if(all.size()>0){
            CPage cPage1 = all.get(0);
            String tid1 = cPage1.getTid();
            cPageComponentsService.findByPageId(tid1);

        }
        return null;
    }
}

