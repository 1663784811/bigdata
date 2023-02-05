package com.cyyaw.tx.web.dao;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.tadmin.entity.TPower;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao  extends BaseDao<TPower, Integer> {


    @Query("select m from TPower m where m.tid in( select t.powerId from TAdminPower t where t.adminId = ?1) or m.ispower = 0 order by m.sort asc ")
    List<TPower> getAdminMenu(String tid);
}
