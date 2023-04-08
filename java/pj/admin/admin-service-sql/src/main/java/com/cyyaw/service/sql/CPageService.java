package com.cyyaw.service.sql;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.config.entity.CPage;

public interface CPageService extends BaseTableService<CPage, Integer> {

    CPage findByTid(String tid);

}
