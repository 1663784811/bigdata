package com.cyyaw.table.admin.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.entity.UGroupUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UGroupUserDao extends BaseDao<UGroupUser, Integer> {

    @Query("select m from UGroupUser m where m.groupid = ?1")
    List<UGroupUser> findByGroupid(String groupid);
}
