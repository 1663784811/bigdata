package com.cyyaw.service.admin;

import com.cyyaw.table.admin.entity.TPower;

import java.util.List;

public interface MenuService {


    List<TPower> getAdminMenu(String tid);
}
