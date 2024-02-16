package com.cyyaw.food.controller;


import com.cyyaw.food.service.FoodOrderService;
import com.cyyaw.food.table.entity.FoodBoard;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "点餐系统")
@RestController
@RequestMapping("/app/{appId}/food/board")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @ApiOperation(value = "开桌", notes = "开桌")
    @PostMapping("/crateBoard")
    public BaseResult crateBoard(@RequestBody FoodBoard foodBoard) {
        Integer number = foodBoard.getNumber();
        String tid = foodBoard.getTid();
        if (number != null && number > 0) {
            return foodOrderService.crateBoard(tid, number);
        } else {
            return BaseResult.fail();
        }
    }

    // 添加或减少菜品,

    // 提交菜品

    // 获取餐桌订单

    // 点击支付

}
