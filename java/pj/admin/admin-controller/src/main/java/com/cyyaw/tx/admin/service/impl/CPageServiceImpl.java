package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.tx.admin.service.CPageService;
import com.cyyaw.table.sql.dao.CPageDao;
import com.cyyaw.table.sql.entity.CPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CPageServiceImpl implements CPageService {

    /**
     *
     */
    @Autowired
    private CPageDao cPageDao;

    @Override
    public List<CPage> findAll() {
        return cPageDao.findAll();
    }
}
