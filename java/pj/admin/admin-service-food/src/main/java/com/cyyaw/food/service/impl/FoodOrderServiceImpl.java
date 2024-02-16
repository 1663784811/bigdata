package com.cyyaw.food.service.impl;

import com.cyyaw.food.service.FoodOrderService;
import com.cyyaw.food.socket.WebSocketServer;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.stereotype.Service;


@Service
public class FoodOrderServiceImpl implements FoodOrderService {


    @Override
    public BaseResult crateBoard(String boardId) {
        // 第一步: 查询餐桌状态

        // 第二步: 发送开桌消息到


        WebSocketServer.crateBoardMessage(boardId);
        return BaseResult.ok();
    }




}
