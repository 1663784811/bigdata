package com.cyyaw.table.store.order.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.store.order.entity.ODetails;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ODetailsDao extends BaseDao<ODetails, Integer> {


    @Query("select m from ODetails  m where m.orderId in (:orderIdArr)")
    List<ODetails> findByOrderIdArr(List<String> orderIdArr);



}
