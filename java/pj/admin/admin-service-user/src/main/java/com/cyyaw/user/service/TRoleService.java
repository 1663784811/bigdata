package com.cyyaw.user.service;


import com.cyyaw.user.table.entity.TRole;

public interface TRoleService {


    TRole save(TRole tRole);

    /**
     * 初始化权限
     */
    void initRole(String enterpriseId, String adminId);


}
