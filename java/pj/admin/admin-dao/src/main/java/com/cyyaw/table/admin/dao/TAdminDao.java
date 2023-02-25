package com.cyyaw.table.admin.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.entity.TAdmin;
import org.springframework.data.jpa.repository.Query;

public interface TAdminDao extends BaseDao<TAdmin, Integer> {

    @Query("select m from TAdmin m where m.enterpriseId = ?1 and m.account = ?2")
    TAdmin findByAccount(String enterpriseId, String account);



}
