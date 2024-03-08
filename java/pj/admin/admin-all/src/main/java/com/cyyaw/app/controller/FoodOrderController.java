package com.cyyaw.app.controller;


import cn.hutool.json.JSONObject;
import com.cyyaw.service.CartService;
import com.cyyaw.service.FoodOrderService;
import com.cyyaw.service.OrderService;
import com.cyyaw.store.table.order.entity.OOrder;
import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.util.entity.AddMyCar;
import com.cyyaw.util.entity.SubmitOrder;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(tags = "点餐系统")
@RestController
@RequestMapping("/app/{appId}/food/board/{boardId}")
public class FoodOrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private FoodOrderService foodOrderService;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "开桌", notes = "开桌")
    @PostMapping("/crateBoard")
    public BaseResult crateBoard(@RequestBody Map<String, Object> map, @PathVariable String boardId) {
        JSONObject json = new JSONObject(map);
        Integer number = json.getInt("number");
        if (number != null && number > 0) {
            return foodOrderService.crateBoard(boardId, number);
        } else {
            return BaseResult.fail();
        }
    }

    // 添加或减少菜品,
    @ApiOperation(value = "更新购物车", notes = "更新购物车")
    @PostMapping("/updateCart")
    public BaseResult updateCar(@RequestBody AddMyCar addMyCar, @PathVariable String boardId) {
        BaseResult result = cartService.updateMyCar(boardId, addMyCar);
        // 通知其它手机
        return result;
    }

    @ApiOperation(value = "查询购物车", notes = "查询购物车")
    @GetMapping("/findMyCart")
    public BaseResult findMyCart(@PathVariable String boardId, String storeId) {
        BaseResult result = cartService.myStoreCartList(boardId, storeId);
        return result;
    }


    // 提交菜品
    @ApiOperation(value = "创建订单", notes = "创建订单")
    @PostMapping("/createOrder")
    public BaseResult createOrder(@RequestBody SubmitOrder submitOrder, @TokenData LoginInfo loginInfo) {
        String userId = loginInfo.getId();
        submitOrder.setUid(userId);
        OOrder order = orderService.createOrder(submitOrder);
        return BaseResult.ok(order);
    }

    // 获取餐桌订单
    @ApiOperation(value = "获取餐桌订单", notes = "获取餐桌订单")
    @GetMapping("/boardOrder")
    public BaseResult boardOrder(@PathVariable String boardId) {
        OOrder order = orderService.boardOrder(boardId);
        return BaseResult.ok(order);
    }
    // 点击支付


}
