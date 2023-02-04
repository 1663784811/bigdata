package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.table.confit.dao.CPageDao;
import com.cyyaw.table.confit.entity.CPage;
import com.cyyaw.tx.admin.service.CPageService;
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
