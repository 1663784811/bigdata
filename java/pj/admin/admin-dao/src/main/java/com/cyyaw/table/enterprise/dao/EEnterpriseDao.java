package com.cyyaw.table.enterprise.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.enterprise.entity.EEnterprise;
import org.springframework.data.jpa.repository.Query;

public interface EEnterpriseDao extends BaseDao<EEnterprise, Integer> {

    @Query("select m from  EEnterprise m where m.tid = ?1")
    EEnterprise findByEnterpriseTid(String tid);

}
