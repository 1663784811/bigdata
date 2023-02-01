package com.cyyaw.service;

import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.goods.GDetails;

public interface GDetailsService extends BaseTableService<GDetails, Integer> {


    /**
     * 查详情
     */
    GDetails findBySkuId(String goodsId);
}
