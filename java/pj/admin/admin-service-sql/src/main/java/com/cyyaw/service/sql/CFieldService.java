package com.cyyaw.service.sql;

import com.cyyaw.table.config.entity.CField;
import java.util.List;

public interface CFieldService {

    List<CField> findByCPageComponentsId(String cPageComponentsTid);

}
