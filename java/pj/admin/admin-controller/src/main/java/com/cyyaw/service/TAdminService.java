package com.cyyaw.service;


import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.tadmin.TAdmin;


public interface TAdminService extends BaseTableService<TAdmin, Integer> {


    TAdmin findByAccount(String name);

}
