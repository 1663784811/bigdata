package com.cyyaw.tx;


import com.cyyaw.store.service.GTypeService;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "订单")
@RestController
@RequestMapping("/shopping/goods/order")
public class ShoppingGoodsOrderController {

    @Autowired
    private GTypeService gTypeService;

    @ApiOperation(value = "订单", notes = "订单")
    @GetMapping("/query")
    public BaseResult query() {
        return BaseResult.ok();
    }

    @ApiOperation(value = "订单详情", notes = "订单详情")
    @GetMapping("/orderById")
    public BaseResult orderById() {
        return BaseResult.ok();
    }

    @ApiOperation(value = "删除订单", notes = "删除订单")
    @GetMapping("/del")
    public BaseResult del() {
        return BaseResult.ok();
    }

}
