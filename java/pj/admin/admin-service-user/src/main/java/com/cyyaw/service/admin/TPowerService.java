package com.cyyaw.service.admin;


import com.cyyaw.table.admin.entity.TPower;

import java.util.List;

public interface TPowerService {

    /**
     * 查询用户权限
     *
     * @param tid
     * @return
     */
    List<TPower> findAdminPower(String tid);


    List<TPower> initPower(String enterpriseId);


}
