package com.cyyaw.tx.web.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.tadmin.entity.TPower;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TPowerSystem  extends BaseDao<TPower,Integer> {

    @Query("select m from TPower m where m.tid in ( select t.tpowerid from TAdminPower t where t.tadminid = ?1)")
    List<TPower> getTPowerByTAdminTid(String tid);

}
