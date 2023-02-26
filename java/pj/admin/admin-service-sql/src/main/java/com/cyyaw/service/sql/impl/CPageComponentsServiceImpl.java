package com.cyyaw.service.sql.impl;

import com.cyyaw.service.sql.CPageComponentsService;
import com.cyyaw.table.config.dao.CPageComponentsDao;
import com.cyyaw.table.config.entity.CPageComponents;
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
