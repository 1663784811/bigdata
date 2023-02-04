package com.cyyaw.table.store.goods.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.store.goods.entity.GCar;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GCarDao extends BaseDao<GCar, Integer> {


    @Query("select m from GCar m where m.userId = ?1 ")
    List<GCar> myCar(String uid, PageRequest of);

    @Query("select m from GCar m where m.userId = ?2 and m.skuId =?1")
    List<GCar> findBySkuIdAndUid(String skuid, String uid);

    @Query("select sum(m.number) from GCar m where m.userId =?1")
    Integer countByUid(String uid);
}
