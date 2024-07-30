package com.cyyaw.web.service;


import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.web.table.entity.EqEquipment;
import com.cyyaw.web.table.entity.WebImageType;

public interface EqEquipmentService extends BaseTableService<EqEquipment, Integer> {

    EqEquipment findByCode(String code);
}
