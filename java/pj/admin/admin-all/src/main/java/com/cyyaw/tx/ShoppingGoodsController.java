package com.cyyaw.tx;
import com.cyyaw.service.ShoppingGoodsService;
import com.cyyaw.store.service.GGoodsSearchService;
import com.cyyaw.store.table.goods.entity.GStoreGoodsSku;
import com.cyyaw.enterprise.table.entity.EStore;
import com.cyyaw.store.table.goods.entity.GGoodsSearch;
import com.cyyaw.store.table.goods.entity.GGoods;


import cn.hutool.core.util.StrUtil;
import com.cyyaw.store.service.GTypeService;
import com.cyyaw.store.table.goods.entity.GType;
import com.cyyaw.user.utils.entity.TreeEntity;
import com.cyyaw.util.entity.GoodsEntity;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(tags = "商品")
@RestController
@RequestMapping("/shopping/goods/search")
public class ShoppingGoodsController {

    @Autowired
    private ShoppingGoodsService shoppingGoodsService;

    @ApiOperation(value = "搜索商品", notes = "搜索商品")
    @GetMapping("/searchGoods")
    public BaseResult searchGoods(GGoodsSearch goodsSearch) {
        return shoppingGoodsService.searchGoods(goodsSearch);
    }


    @ApiOperation(value = "商品详情", notes = "商品详情")
    @GetMapping("/goodsDetails")
    public BaseResult goodsDetails(String goodsId) {
        return shoppingGoodsService.goodsDetails(goodsId);
    }






}