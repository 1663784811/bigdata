package com.cyyaw.tx.web.service;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.admin.tadmin.UUser;

public interface UUserService extends BaseTableService<UUser, Integer> {


    UUser findByAccountAndPassword(String account, String password);

    /**
     * 根据用户名查找用户
     * @param account
     * @return
     */
    UUser findByAccount(String account);

    UUser upateUserByWxMaUserInfo(WxMaUserInfo wxMaUserInfo);
}
