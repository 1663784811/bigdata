package com.cyyaw.tx;


import com.cyyaw.service.CartService;
import com.cyyaw.util.entity.AddMyCar;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "购物车")
@RestController
@RequestMapping("/shopping/goods/cart")
public class ShoppingGoodsCarController {

    @Autowired
    private CartService cartService;

    @ApiOperation(value = "购物车", notes = "购物车")
    @GetMapping("/query")
    public BaseResult query() {
        String userId = "4f3c48b31e4d477689cca9db53c173fa";

        return cartService.myCartList(userId);
    }

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @GetMapping("/del")
    public BaseResult del() {
        return BaseResult.ok();
    }

    @ApiOperation(value = "更新购物车", notes = "更新购物车")
    @PostMapping("/updateCart")
    public BaseResult updateCar(@RequestBody AddMyCar addMyCar) {
        return cartService.updateMyCar("4f3c48b31e4d477689cca9db53c173fa", addMyCar);
    }


}
