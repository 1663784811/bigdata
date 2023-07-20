package com.cyyaw.store.table.order.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.store.table.order.entity.OOrder;
import org.springframework.data.jpa.repository.Query;

public interface OOrderDao extends BaseDao<OOrder, Integer> {



    @Query("select m from OOrder m where m.tid = ?1")
    OOrder findByTid(String tid);

}
