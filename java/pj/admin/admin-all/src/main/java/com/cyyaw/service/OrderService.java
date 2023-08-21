package com.cyyaw.service;

import com.cyyaw.util.entity.CountGoodsRst;
import com.cyyaw.util.entity.SubmitOrder;

public interface OrderService {

    CountGoodsRst countGoodsPrice(SubmitOrder submitOrder);


    void createOrder(SubmitOrder submitOrder);


}
