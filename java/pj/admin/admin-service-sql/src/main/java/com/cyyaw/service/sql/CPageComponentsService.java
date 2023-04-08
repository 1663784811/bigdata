package com.cyyaw.service.sql;


import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.config.entity.CPageComponents;

import java.util.List;

public interface CPageComponentsService extends BaseTableService<CPageComponents, Integer> {

    List<CPageComponents> findByPageId(String pageId);

}
