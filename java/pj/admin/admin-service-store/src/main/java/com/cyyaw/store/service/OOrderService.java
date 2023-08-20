package com.cyyaw.store.service;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.store.table.order.entity.OOrder;
import com.cyyaw.util.tools.BaseResult;

public interface OOrderService extends BaseTableService<OOrder, Integer> {


    /**
     * 查询订单列表
     */
    BaseResult shoppingMyOrder();

    /**
     * 查询订单详情
     */
    BaseResult orderById(String orderId);


}
