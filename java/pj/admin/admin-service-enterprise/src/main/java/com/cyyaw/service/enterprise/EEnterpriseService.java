package com.cyyaw.service.enterprise;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.enterprise.entity.EEnterprise;

public interface EEnterpriseService extends BaseTableService<EEnterprise, Integer> {

    /**
     * 企业注册
     */
    EEnterprise registerEnterprise(EEnterprise enterprise);


}
