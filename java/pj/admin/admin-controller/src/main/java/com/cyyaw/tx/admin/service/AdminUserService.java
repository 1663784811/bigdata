package com.cyyaw.tx.admin.service;

import com.cyyaw.entity.TreeEntity;
import com.cyyaw.table.admin.tadmin.entity.TPower;

import java.util.List;

public interface AdminUserService {

    /**
     * 获取用户菜单
     * @return
     */
    List<TreeEntity.Node<TPower>> adminMenu(String adminId);


}
