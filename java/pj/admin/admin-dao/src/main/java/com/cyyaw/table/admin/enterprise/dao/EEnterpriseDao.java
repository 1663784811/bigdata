package com.cyyaw.table.admin.enterprise.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.enterprise.entity.EEnterprise;
import org.springframework.data.jpa.repository.Query;

public interface EEnterpriseDao extends BaseDao<EEnterprise, Integer> {

    @Query("select m from  EEnterprise m where m.tid = ?1")
    EEnterprise findByEnterpriseTid(String tid);

}
