package com.cyyaw.service.admin;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.admin.entity.TAdmin;

public interface TAdminService extends BaseTableService<TAdmin, Integer> {


    TAdmin findByTid(String tid);

}
