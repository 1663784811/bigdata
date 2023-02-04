package com.cyyaw.tx.admin.service;


import com.cyyaw.table.confit.entity.CField;

import java.util.List;

public interface CFieldService {

    List<CField> findByCPageComponentsId(String cPageComponentsTid);

}
