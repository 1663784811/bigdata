package com.cyyaw.service;

import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.enterprise.EEnterprise;


public interface EEnterpriseService extends BaseTableService<EEnterprise, Integer> {


    EEnterprise registerEnterprise(EEnterprise enterprise);
}
