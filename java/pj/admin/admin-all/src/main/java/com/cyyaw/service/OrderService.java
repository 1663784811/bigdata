package com.cyyaw.service;

import com.cyyaw.util.entity.SubmitOrder;

public interface OrderService {

    void countGoodsPrice(SubmitOrder submitOrder);


    void createOrder(SubmitOrder submitOrder);


}
