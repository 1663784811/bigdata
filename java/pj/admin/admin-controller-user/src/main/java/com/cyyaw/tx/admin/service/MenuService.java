package com.cyyaw.tx.admin.service;

import com.cyyaw.table.admin.entity.TPower;

import java.util.List;

public interface MenuService {


    List<TPower> getAdminMenu(String tid);
}
