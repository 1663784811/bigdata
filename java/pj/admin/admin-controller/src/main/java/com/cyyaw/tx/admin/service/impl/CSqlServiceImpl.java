package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.table.confit.dao.CSqlDao;
import com.cyyaw.table.confit.entity.CSql;
import com.cyyaw.tx.admin.service.CSqlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CSqlServiceImpl implements CSqlService {


    @Autowired
    private CSqlDao cSqlDao;

    @Override
    public List<CSql> findAll() {
        return cSqlDao.findAll();
    }
}
