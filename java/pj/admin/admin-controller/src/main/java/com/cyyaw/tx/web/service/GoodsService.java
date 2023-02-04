package com.cyyaw.tx.web.service;

import com.cyyaw.entity.GoodsEntity;
import com.cyyaw.table.store.goods.GGoodsSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodsService {

    /**
     * 搜索商品
     */
    Page<GGoodsSearch> searchGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size);

    /**
     * 获取首页商品列表
     */
    List<GoodsEntity> indexGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size);

    /**
     * 查询商品
     * @param id ID
     */
    GoodsEntity findGoodsById(Integer id);

    /**
     * 查询商品
     * @param skuid ID
     * @return
     */
    GoodsEntity findGoodsBySkuId(Integer skuid);
}
