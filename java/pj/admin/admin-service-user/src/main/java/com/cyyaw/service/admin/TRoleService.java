package com.cyyaw.service.admin;


import com.cyyaw.table.admin.entity.TRole;

public interface TRoleService {


    TRole save(TRole tRole);

    /**
     * 初始化权限
     */
    void initRole(String enterpriseId, String adminId);


}
