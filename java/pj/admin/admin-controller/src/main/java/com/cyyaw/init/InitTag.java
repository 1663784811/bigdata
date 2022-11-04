package com.cyyaw.init;


import com.cyyaw.table.sql.entity.CPage;
import org.springframework.stereotype.Service;

@Service
public class InitTag {




    public void getId01() {

        CPage cPage = new CPage();
        cPage.setTid("aa");
        cPage.setName("首页");
        cPage.setPageIcon("");
        cPage.setPageCode("index");

    }
}
