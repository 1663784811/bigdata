package com.cyyaw.store.table.goods.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.store.table.goods.entity.GStoreGoodsSku;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GStoreGoodsSkuDao extends BaseDao<GStoreGoodsSku, Integer> {


    @Query("select m from GStoreGoodsSku m where m.goodsid in( :goodsids )")
    List<GStoreGoodsSku> findAllByStoreAndGoodsid(List<String> goodsids);

    @Query("select m from GStoreGoodsSku m where m.goodsid in (:goodsids)")
    List<GStoreGoodsSku> findAllByGoodsid(List<String> goodsids);

    @Query("select m from GStoreGoodsSku m where m.tid =?1")
    GStoreGoodsSku findByTid(String skuid);

    @Query("select m from GStoreGoodsSku m where m.tid in (:tidArr)")
    List<GStoreGoodsSku> findByTidIn(List<String> tidArr);

}
