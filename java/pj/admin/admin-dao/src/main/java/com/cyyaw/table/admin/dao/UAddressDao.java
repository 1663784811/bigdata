package com.cyyaw.table.admin.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.entity.UAddress;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UAddressDao extends BaseDao<UAddress, Integer> {


    @Query("select m from  UAddress  m where  m.userid = ?1")
    List<UAddress> findAll(String uid);
}
