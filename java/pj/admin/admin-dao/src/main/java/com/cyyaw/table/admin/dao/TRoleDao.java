package com.cyyaw.table.admin.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.entity.TRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TRoleDao extends BaseDao<TRole, Integer> {


    @Query("select m from TRole m where m.tid in ( select t.roleId from TAdminRole t where t.adminId = ?1)")
    List<TRole> findByAdminId(String tid);
}