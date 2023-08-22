package com.cyyaw.service;

import com.cyyaw.store.table.order.entity.OOrder;
import com.cyyaw.util.entity.CountGoodsRst;
import com.cyyaw.util.entity.SubmitOrder;

public interface OrderService {

    CountGoodsRst countGoodsPrice(SubmitOrder submitOrder);


    OOrder createOrder(SubmitOrder submitOrder);


}
