package com.cyyaw.service.admin.impl;

import com.cyyaw.table.admin.dao.TPowerDao;
import com.cyyaw.table.admin.entity.TPower;
import com.cyyaw.service.admin.MenuService;
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
