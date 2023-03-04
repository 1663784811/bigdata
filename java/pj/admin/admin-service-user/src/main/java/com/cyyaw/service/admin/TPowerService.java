package com.cyyaw.service.admin;


import com.cyyaw.table.admin.entity.TPower;

import java.util.List;

public interface TPowerService {

    /**
     * 查询用户权限
     */
    List<TPower> findAdminPower(String tid);

    /**
     * 初始化菜单
     */
    List<TPower> initPower(String enterpriseId);


}
