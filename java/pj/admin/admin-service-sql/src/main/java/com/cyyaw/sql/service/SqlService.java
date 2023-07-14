package com.cyyaw.sql.service;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.sql.table.entity.CSql;
import com.cyyaw.util.tools.BaseResult;

public interface SqlService {

    BaseResult<CSql> sqlList(SelectEntity select);

    CSql save(CSql cSql);

    void delSql(Integer[] idArr);

}
