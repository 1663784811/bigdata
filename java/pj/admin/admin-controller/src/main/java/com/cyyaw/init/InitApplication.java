package com.cyyaw.init;
import java.util.Date;

import com.cyyaw.table.sql.dao.CPageComponentsDao;
import com.cyyaw.table.sql.dao.CPageDao;
import com.cyyaw.table.sql.entity.CPage;
import com.cyyaw.table.sql.entity.CPageComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 初始化
 */
@Service
public class InitApplication {

    @Autowired
    private CPageDao pageDao;

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    public void init() {

        CPage cPage = new CPage();
        cPage.setTid("aa");
        cPage.setName("首页");
        cPage.setPageIcon("");
        cPage.setPageCode("index");

        pageDao.save(cPage);


        CPageComponents components = new CPageComponents();
        components.setId(0);
        components.setTid("aa");
        components.setPageId("aa");
        components.setName("表格组件");
        components.setComponents_code("aa");
        components.setData("");
        components.setSort(0);



        cPageComponentsDao.save(components);



    }
}
