package com.cyyaw.table.admin.tadmin.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.tadmin.entity.TAdmin;
import org.springframework.data.jpa.repository.Query;

public interface TAdminDao extends BaseDao<TAdmin, Integer> {

    @Query("select m from TAdmin m where m.account = ?1")
    TAdmin findByAccount(String account);



}