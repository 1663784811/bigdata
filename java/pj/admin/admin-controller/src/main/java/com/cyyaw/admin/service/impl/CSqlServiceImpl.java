package com.cyyaw.admin.service.impl;

import com.cyyaw.admin.service.CSqlService;
import com.cyyaw.table.sql.dao.CSqlDao;
import com.cyyaw.table.sql.entity.CSql;
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
