package com.cyyaw.table.admin.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.entity.TAdmin;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TAdminDao extends BaseDao<TAdmin, Integer> {

    @Query("select m from TAdmin m where m.enterpriseId = ?1 and m.account = ?2")
    TAdmin findByAccount(String enterpriseId, String account);

    @Query("select m from TAdmin m where (m.account=?1 or m.phone=?1 or m.email=?1) and m.enterpriseId = ?2")
    List<TAdmin> getLoignInfo(String account, String enterpriseId);


}
