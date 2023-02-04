package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.table.confit.dao.CFieldDao;
import com.cyyaw.table.confit.entity.CField;
import com.cyyaw.tx.admin.service.CFieldService;
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
