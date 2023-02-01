package com.cyyaw.service;


import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.tadmin.TRole;

import java.util.List;

public interface TRoleService extends BaseTableService<TRole, Integer> {


    List<TRole> findTRoleByAdminId(String adminId);

}
