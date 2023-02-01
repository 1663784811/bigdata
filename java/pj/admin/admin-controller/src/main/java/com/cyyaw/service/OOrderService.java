package com.cyyaw.service;

import com.cyyaw.config.common.entity.OrderEntity;
import com.cyyaw.config.common.entity.SubmitOrder;
import com.cyyaw.config.common.entity.SubmitOrderRest;
import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.order.OOrder;

import java.util.List;

public interface OOrderService extends BaseTableService<OOrder, Integer> {


    /**
     * 获取我的订单
     * @param uid 用户ID
     * @param page  分页
     * @param size  大小
     * @param status  状态
     */
    List<OrderEntity> myOrder(String uid, Integer page, Integer size, Integer status);

    /**
     * 我的订单详情
     * @param id 订单ID
     * @return
     */
    OrderEntity myOrderDetails(Integer id);

    /**
     * 提交订单
     * @param submitOrder
     */
    SubmitOrderRest submitOrder(SubmitOrder submitOrder);


    OOrder findByTid(String tid);

}
