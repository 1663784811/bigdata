package com.cyyaw.tx;


import cn.hutool.core.util.StrUtil;
import com.cyyaw.store.service.GTypeService;
import com.cyyaw.store.table.goods.entity.GType;
import com.cyyaw.user.utils.entity.TreeEntity;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "购物车")
@RestController
@RequestMapping("/shopping/goods/car")
public class ShoppingGoodsCarController {

    @Autowired
    private GTypeService gTypeService;

    @ApiOperation(value = "购物车", notes = "购物车")
    @GetMapping("/query")
    public BaseResult query() {
        return BaseResult.ok();
    }

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @GetMapping("/del")
    public BaseResult del() {
        return BaseResult.ok();
    }

    @ApiOperation(value = "更新购物车", notes = "更新购物车")
    @GetMapping("/updateCar")
    public BaseResult updateCar() {
        return BaseResult.ok();
    }


}
