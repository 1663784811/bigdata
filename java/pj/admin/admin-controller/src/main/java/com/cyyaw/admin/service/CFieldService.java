package com.cyyaw.admin.service;

import com.cyyaw.table.sql.entity.CField;

import java.util.List;

public interface CFieldService {

    List<CField> findByCPageComponentsId(String cPageComponentsTid);

}
