package com.cyyaw.table.confit.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.confit.entity.CSql;
import org.springframework.data.jpa.repository.Query;

public interface CSqlDao extends BaseDao<CSql, Integer> {


    @Query("select m from CSql m where m.tid = ?1")
    CSql findByTid(String tid);


}