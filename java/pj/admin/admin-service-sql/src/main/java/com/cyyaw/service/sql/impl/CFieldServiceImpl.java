package com.cyyaw.service.sql.impl;

import com.cyyaw.service.sql.CFieldService;
import com.cyyaw.table.config.dao.CFieldDao;
import com.cyyaw.table.config.entity.CField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CFieldServiceImpl implements CFieldService {

    @Autowired
    private CFieldDao cFieldDao;


    @Override
    public List<CField> findByCPageComponentsId(String cPageComponentsTid) {
        return cFieldDao.findByCPageComponentsId(cPageComponentsTid);
    }
}
