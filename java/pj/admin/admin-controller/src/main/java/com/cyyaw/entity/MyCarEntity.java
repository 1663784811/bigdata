package com.cyyaw.entity;

import com.cyyaw.table.goods.GCar;
import com.cyyaw.table.goods.GGoods;
import com.cyyaw.table.goods.GStoreGoodsSku;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 我的购物车
 */
@Data
public class MyCarEntity {


    /**
     * 购物车数据
     */
    private GCar gCar;

    /**
     * 商品数据
     */
    private GGoods gGoods;

    /**
     * 已经选择的sku
     */
    @ApiModelProperty(value = "gStoreGoodsSku")
    private GStoreGoodsSku gStoreGoodsSku;

    /**
     * sku 列表
     */
    @ApiModelProperty(value = "gStoreGoodsSkuList")
    private List<GStoreGoodsSku> gStoreGoodsSkuList;

}
