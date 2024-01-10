package com.cyyaw.user.service;


import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.user.table.entity.UUser;

public interface UUserService extends BaseTableService<UUser, Integer> {

    /**
     * 查询APP下的用户
     */
    UUser findByAppIdAndPassword(String appId, String account);

    /**
     * 根据用户名查找用户
     * @param account
     * @return
     */
    UUser findByAccount(String account);

    /**
     *
     */
    UUser findByTid(String tid);


}
