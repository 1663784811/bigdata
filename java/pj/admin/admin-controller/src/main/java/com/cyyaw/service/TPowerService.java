package com.cyyaw.service;


import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.tadmin.TPower;

import java.util.List;

public interface TPowerService extends BaseTableService<TPower, Integer> {

    /**
     * 查询用户权限
     * @param tid
     * @return
     */
    List<TPower> findAdminPower(String tid);
}
