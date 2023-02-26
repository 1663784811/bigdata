package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.table.admin.dao.TPowerDao;
import com.cyyaw.table.admin.entity.TPower;
import com.cyyaw.tx.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private TPowerDao tPowerDao;


    @Override
    public List<TPower> getAdminMenu(String tid) {
        return tPowerDao.getAdminMenu(tid);
    }

}
