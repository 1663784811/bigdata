package com.cyyaw.service;

import com.cyyaw.config.common.entity.ComputGoods;
import com.cyyaw.config.common.entity.ComputGoodsRst;
import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.goods.GStoreGoodsSku;

import java.util.List;

public interface GStoreGoodsSkuService extends BaseTableService<GStoreGoodsSku, Integer> {


    List<GStoreGoodsSku> saveGStoreGoodsSkuList(List<GStoreGoodsSku> gStoreGoodsSkuList);


    ComputGoodsRst computGoods(List<ComputGoods> list);


}
