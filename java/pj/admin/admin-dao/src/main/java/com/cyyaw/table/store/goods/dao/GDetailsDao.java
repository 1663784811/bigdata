package com.cyyaw.table.store.goods.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.store.goods.entity.GDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GDetailsDao extends BaseDao<GDetails, Integer> {

    @Query("select m from GDetails m where m.goodsId = ?1")
    List<GDetails> findByGoodsid(String goodsId);

}
