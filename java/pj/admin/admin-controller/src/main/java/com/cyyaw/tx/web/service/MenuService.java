package com.cyyaw.tx.web.service;


import com.cyyaw.table.admin.tadmin.TPower;

import java.util.List;

public interface MenuService {


    List<TPower> getAdminMenu(String tid);
}
