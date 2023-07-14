package com.cyyaw.service.impl;

import com.cyyaw.entity.TreeEntity;
import com.cyyaw.user.table.entity.TPower;

import java.util.List;

public interface AdminUserService {

    /**
     * 获取用户菜单
     * @return
     */
    List<TreeEntity.Node<TPower>> adminMenu(String adminId);


}
