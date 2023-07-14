package com.cyyaw.user.service;


import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.user.table.entity.TPower;

import java.util.List;

public interface TPowerService extends BaseTableService<TPower, Integer> {

    /**
     * 查询用户权限
     */
    List<TPower> findAdminPower(String tid);

    /**
     * 初始化菜单
     */
    List<TPower> initPower(String enterpriseId);


}
