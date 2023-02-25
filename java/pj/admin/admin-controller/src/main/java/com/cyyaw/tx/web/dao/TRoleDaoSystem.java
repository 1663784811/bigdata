package com.cyyaw.tx.web.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.entity.TAdmin;
import com.cyyaw.table.admin.entity.TRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TRoleDaoSystem extends BaseDao<TAdmin,Integer> {

    @Query("select m from TRole m where m.tid in ( select s.roleId from TAdminRole s where s.adminId = ?1 )")
    List<TRole> getRolesByTAdminTid(String tid);
}
