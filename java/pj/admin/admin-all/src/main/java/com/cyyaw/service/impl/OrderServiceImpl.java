package com.cyyaw.service.impl;

import com.cyyaw.service.OrderService;
import com.cyyaw.util.entity.CountGoods;
import com.cyyaw.util.entity.SubmitOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Override
    public void countGoodsPrice(SubmitOrder submitOrder) {
        List<CountGoods> goodsList = submitOrder.getGoodsList();
        // 查询sku


        // 分门店

        //  判断数量


        // 单价 * 数量  = 总价

        // 运费

        //


    }

    @Override
    public void createOrder(SubmitOrder submitOrder) {
        // 判断基本信息是否正确

        // 计算价格


        // 生成订单


    }




}
