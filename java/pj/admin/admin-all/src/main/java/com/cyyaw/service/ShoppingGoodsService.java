package com.cyyaw.service;

import com.cyyaw.store.table.goods.entity.GGoodsSearch;
import com.cyyaw.util.entity.GoodsEntity;
import com.cyyaw.util.tools.BaseResult;

import java.util.List;

public interface ShoppingGoodsService {

    BaseResult<List<GoodsEntity>> searchGoods(GGoodsSearch goodsSearch);


    BaseResult goodsDetails(String goodsId);


}