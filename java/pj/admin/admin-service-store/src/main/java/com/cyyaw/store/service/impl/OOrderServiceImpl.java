package com.cyyaw.store.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.store.service.OOrderService;
import com.cyyaw.store.table.order.dao.ODetailsDao;
import com.cyyaw.store.table.order.dao.OOrderDao;
import com.cyyaw.store.table.order.entity.ODetails;
import com.cyyaw.store.table.order.entity.OOrder;
import com.cyyaw.store.utils.entity.UserOrderResponse;
import com.cyyaw.util.tools.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class OOrderServiceImpl extends BaseService<OOrder, Integer> implements OOrderService {

    @Autowired
    private OOrderDao oOrderDao;

    @Autowired
    private ODetailsDao oDetailsDao;

    @Override
    public BaseDao getBaseDao() {
        return oOrderDao;
    }

    @Override
    public BaseResult shoppingMyOrder() {
        // 查询我的订单

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<OOrder> oOrderPage = oOrderDao.findAll(pageRequest);
        List<OOrder> orderList = oOrderPage.getContent();

        BaseResult.Result result = new BaseResult.Result();
        result.setPage(oOrderPage.getTotalPages());
        result.setSize(oOrderPage.getSize());
        result.setTotal(oOrderPage.getTotalElements());
        //=====
        List<String> orderIdList = new ArrayList<>();
        for (OOrder order : orderList) {
            orderIdList.add(order.getTid());
        }

        //=====  查询订单详情
        List<ODetails> oDetailsList = oDetailsDao.findByOrderIdArr(orderIdList);


        //=====  整理数据
        List<UserOrderResponse> data = new ArrayList<>();
        for (OOrder order : orderList) {
            String tid = order.getTid();
            UserOrderResponse userOrderResponse = new UserOrderResponse();
            userOrderResponse.setOrder(order);
            List<ODetails> oDetails = new ArrayList<>();
            for (int i = 0; i < oDetailsList.size(); i++) {
                ODetails details = oDetailsList.get(i);
                if (tid.equals(details.getOrderId())) {
                    oDetails.add(details);
                }
            }
            userOrderResponse.setDetailsList(oDetails);
            data.add(userOrderResponse);
        }
        return BaseResult.ok(data, result);
    }

    @Override
    public BaseResult orderById(String orderId) {
        OOrder order = oOrderDao.findByTid(orderId);
        List<ODetails> oDetailsList = oDetailsDao.findByOrderId(orderId);
        UserOrderResponse data = new UserOrderResponse();
        data.setOrder(order);
        data.setDetailsList(oDetailsList);
        return BaseResult.ok(data);
    }
}

