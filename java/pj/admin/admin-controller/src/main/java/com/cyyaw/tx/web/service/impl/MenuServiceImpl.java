package com.cyyaw.tx.web.service.impl;

import com.cyyaw.table.admin.tadmin.entity.TPower;
import com.cyyaw.tx.web.dao.MenuDao;
import com.cyyaw.tx.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuDao menuDao;


    @Override
    public List<TPower> getAdminMenu(String tid) {
        return menuDao.getAdminMenu(tid);
    }

}
