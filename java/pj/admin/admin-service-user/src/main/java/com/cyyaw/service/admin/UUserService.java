package com.cyyaw.service.admin;


import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.admin.entity.UUser;

public interface UUserService {

    UUser save(UUser uUser);




    UUser findByAccountAndPassword(String account, String password);

    /**
     * 根据用户名查找用户
     * @param account
     * @return
     */
    UUser findByAccount(String account);

    UUser upateUserByWxMaUserInfo(WxMaUserInfo wxMaUserInfo);



}