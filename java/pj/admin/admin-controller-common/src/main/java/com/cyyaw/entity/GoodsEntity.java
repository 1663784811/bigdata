package com.cyyaw.entity;

import com.cyyaw.enterprise.table.entity.EStore;
import com.cyyaw.table.store.goods.entity.GGoods;
import com.cyyaw.table.store.goods.entity.GGoodsSearch;
import com.cyyaw.table.store.goods.entity.GStoreGoodsSku;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsEntity {

    /**
     * 商品
     */
    @ApiModelProperty(value = "gGoods")
    private GGoods gGoods;
    /**
     * 商品
     */
    @ApiModelProperty(value = "goodsSearch")
    private GGoodsSearch goodsSearch;

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

    /**
     * 门店
     */
    @ApiModelProperty(value = "eStore")
    private EStore eStore;

}
