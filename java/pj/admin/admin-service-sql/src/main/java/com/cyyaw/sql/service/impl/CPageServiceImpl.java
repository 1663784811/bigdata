package com.cyyaw.sql.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.sql.service.CPageComponentsService;
import com.cyyaw.sql.service.CPageService;
import com.cyyaw.sql.table.dao.CPageDao;
import com.cyyaw.sql.table.entity.CPage;
import com.cyyaw.sql.table.entity.CPageComponents;
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
        if (all.size() > 0) {
            CPage cPage1 = all.get(0);
            String tid1 = cPage1.getTid();
            CPageComponents ex = new CPageComponents();
            ex.setPageId(tid1);
            List<CPageComponents> cPageComponentsList = cPageComponentsService.findByExample(ex);
            cPage1.setComponents(cPageComponentsList);
            return cPage1;
        }
        return null;
    }

    @Override
    public CPage findByPageCode(String pageCod) {
        CPage cPage = new CPage();
        cPage.setPageCode(pageCod);
        Example<CPage> example = Example.of(cPage);
        List<CPage> all = cPageDao.findAll(example);
        if (all.size() > 0) {
            CPage cPage1 = all.get(0);
            String pageId = cPage1.getTid();
            List<CPageComponents> cPageComponentsList = cPageComponentsService.findByPageId(pageId);
            cPage1.setComponents(cPageComponentsList);
            return cPage1;
        }
        return null;
    }
}

