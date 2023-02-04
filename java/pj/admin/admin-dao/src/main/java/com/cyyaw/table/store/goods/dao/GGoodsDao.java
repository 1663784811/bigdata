package com.cyyaw.table.store.goods.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.store.goods.entity.GGoods;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GGoodsDao extends BaseDao<GGoods, Integer> {


    @Query("select m from GGoods m where m.tid in ( :goodsid ) ")
    List<GGoods> findByTidIn(@Param("goodsid") List<String> goodsids);

    @Query("select m from GGoods m where m.tid = ?1")
    GGoods findByTid(String tid);

    @Query("select m from GGoods m where m.tid not in( select t.goodsId from GGoodsSearch t )")
    List<GGoods> findPageGGoodsNoSearch(PageRequest of);
}
