package com.cyyaw.tx.admin.service;

import com.cyyaw.entity.TreeEntity;

public interface AdminUserService {

    /**
     * 获取用户菜单
     * @return
     */
    TreeEntity adminMenu(String adminId);


}
