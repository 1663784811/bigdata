package com.cyyaw.web.table.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.web.table.entity.EqEquipment;
import com.cyyaw.web.table.entity.WBanner;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EqEquipmentDao extends BaseDao<EqEquipment, Integer> {


    @Query("select m from EqEquipment m where m.code = ?1")
    List<EqEquipment> findByCode(String code);


}
