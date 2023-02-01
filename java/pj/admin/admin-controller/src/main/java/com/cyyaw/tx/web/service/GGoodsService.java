package com.cyyaw.tx.web.service;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.table.goods.GDetails;
import com.cyyaw.table.goods.GGoods;
import com.cyyaw.table.goods.GPhoto;
import com.cyyaw.table.goods.GStoreGoodsSku;
import com.cyyaw.util.tools.PageRespone;

public interface GGoodsService extends BaseTableService<GGoods, Integer> {
    /**
     * 保存商品
     */
    GGoods saveGoods(GGoods gGoods, List<GPhoto> photoList, List<GStoreGoodsSku> skuList, GDetails gDetails);

    GGoods findByTid(String tid);

    PageRespone<GGoods> findPageGGoodsNoSearch(SelectEntity selectEntity);
}
