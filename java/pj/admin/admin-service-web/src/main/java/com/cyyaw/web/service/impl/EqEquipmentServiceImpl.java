package com.cyyaw.web.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.web.service.EqEquipmentService;
import com.cyyaw.web.service.WebImageTypeService;
import com.cyyaw.web.table.dao.EqEquipmentDao;
import com.cyyaw.web.table.dao.WebImageTypeDao;
import com.cyyaw.web.table.entity.EqEquipment;
import com.cyyaw.web.table.entity.WebImageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class EqEquipmentServiceImpl extends BaseService<EqEquipment, Integer> implements EqEquipmentService {

    @Autowired
    private EqEquipmentDao eqEquipmentDao;

    @Override
    public BaseDao getBaseDao() {
        return eqEquipmentDao;
    }


    @Override
    public EqEquipment findByCode(String code) {
        List<EqEquipment> equipmentList = eqEquipmentDao.findByCode(code);
        int size = equipmentList.size();
        if (size == 1) {
            return equipmentList.get(0);
        } else if (size > 1) {
            log.info("存在多个设备code");
        }

        return null;
    }
}

