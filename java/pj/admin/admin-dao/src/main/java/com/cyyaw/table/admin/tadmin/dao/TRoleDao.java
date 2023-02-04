package com.cyyaw.table.admin.tadmin.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.tadmin.entity.TRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TRoleDao extends BaseDao<TRole, Integer> {


    @Query("select m from TRole m where m.id in ( select t.troleid from TAdminRole t where t.tadminid = ?1)")
    List<TRole> findByAdminId(String tid);
}
