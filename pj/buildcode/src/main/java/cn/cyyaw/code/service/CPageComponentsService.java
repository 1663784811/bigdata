package com.cyyaw.code.service;

import com.cyyaw.code.table.entity.CPageComponents;
import com.cyyaw.jpa.BaseTableService;

import java.util.List;

public interface CPageComponentsService extends BaseTableService<CPageComponents, Integer> {


    List<CPageComponents> findByExample(CPageComponents cPageComponents);
}
