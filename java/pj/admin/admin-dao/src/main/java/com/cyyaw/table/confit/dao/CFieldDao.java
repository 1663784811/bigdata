package com.cyyaw.table.confit.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.confit.entity.CField;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CFieldDao extends BaseDao<CField, Integer> {


    @Query("select m from CField m where m.cPageComponentsId = ?1")
    List<CField> findByCPageComponentsId(String cPageComponentsTid);

}