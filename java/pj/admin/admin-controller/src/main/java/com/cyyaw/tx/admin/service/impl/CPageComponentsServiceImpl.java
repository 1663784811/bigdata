package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.table.confit.dao.CPageComponentsDao;
import com.cyyaw.table.confit.entity.CPageComponents;
import com.cyyaw.tx.admin.service.CPageComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CPageComponentsServiceImpl implements CPageComponentsService {

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    @Override
    public List<CPageComponents> findAll() {
        return cPageComponentsDao.findAll();
    }
}
