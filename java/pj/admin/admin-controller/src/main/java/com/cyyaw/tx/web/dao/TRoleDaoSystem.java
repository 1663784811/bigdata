package com.cyyaw.tx.web.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.tadmin.entity.TAdmin;
import com.cyyaw.table.admin.tadmin.entity.TRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TRoleDaoSystem extends BaseDao<TAdmin,Integer> {

    @Query("select m from TRole m where m.tid in ( select s.troleid from TAdminRole s where s.tadminid = ?1 )")
    List<TRole> getRolesByTAdminTid(String tid);
}
