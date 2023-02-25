package com.cyyaw.tx.admin.service;

import com.cyyaw.entity.SelectEntity;
import com.cyyaw.table.config.entity.CSql;
import com.cyyaw.util.tools.BaseResult;

public interface SqlService {

    BaseResult<CSql> sqlList(SelectEntity select);

    CSql save(CSql cSql);

    void delSql(Integer[] idArr);

}
