package com.cyyaw.table.admin.tadmin.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.tadmin.entity.UGroup;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UGroupDao extends BaseDao<UGroup, Integer> {

    @Query("select m from UGroup m where m.tid in (select t.groupid from UGroupUser t where t.userid = ?1 ) ")
    List<UGroup> findByUserid(String userid);


    UGroup findByTid(String tid);

}
