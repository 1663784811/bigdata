package com.cyyaw.user.service;


import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.user.table.entity.UUser;

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
