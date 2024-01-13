package com.cyyaw.food.controller;


import com.cyyaw.food.service.FoodOrderService;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "点餐系统")
@RestController
@RequestMapping("/app/{appId}/food/order")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @ApiOperation(value = "开桌", notes = "开桌")
    @PostMapping("/crateBoard")
    public BaseResult crateBoard(String boardId) {
        return foodOrderService.crateBoard(boardId);
    }

    // 添加或减少菜品

    // 提交菜品

    // 获取餐桌订单

    // 点击支付
    
}
