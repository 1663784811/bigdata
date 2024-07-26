package com.cyyaw.store.table.goods.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.store.table.goods.entity.GdDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GdDetailsDao extends BaseDao<GdDetails, Integer> {

    @Query("select m from GdDetails m where m.goodsId = ?1")
    List<GdDetails> findByGoodsId(String goodsId);

}
