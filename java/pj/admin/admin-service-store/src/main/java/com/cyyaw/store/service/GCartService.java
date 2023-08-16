package com.cyyaw.store.service;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.store.table.goods.entity.GCart;
import com.cyyaw.util.tools.BaseResult;

public interface GCartService extends BaseTableService<GCart, Integer> {

    /**
     * 获取我的购物车列表
     */
    BaseResult myCartList();


}
