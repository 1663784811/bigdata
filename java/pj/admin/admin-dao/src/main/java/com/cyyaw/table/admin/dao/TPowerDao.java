package com.cyyaw.table.admin.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.entity.TPower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TPowerDao extends BaseDao<TPower, Integer> {

    @Query("select m from TPower m where m.tid in ( select t from TAdminPower t where t.adminId = ?1)")
    List<TPower> findAdminPower(String tid);


    @Query("select m from TPower m where m.tid in ( select t.powerId from TRolePower t where t.roleId in (:rolesId))")
    List<TPower> findPowerByRole(@Param("rolesId") List<String> rolesId);


    @Query("select m from TPower m where m.tid in( select t.powerId from TAdminPower t where t.adminId = ?1) or m.isPower = 0 order by m.sort asc ")
    List<TPower> getAdminMenu(String tid);


    @Query("select m from TPower m where m.tid in ( select t.powerId from TAdminPower t where t.adminId = ?1)")
    List<TPower> getTPowerByTAdminTid(String tid);



}
