package com.cyyaw.tx.admin.service;

import com.cyyaw.entity.SelectEntity;
import com.cyyaw.table.confit.entity.CSql;
import com.cyyaw.util.tools.BaseResult;

public interface SqlService {

    BaseResult<CSql> sqlList(SelectEntity select);

}
