package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.table.admin.tadmin.dao.TPowerDao;
import com.cyyaw.table.admin.tadmin.dao.TRoleDao;
import com.cyyaw.table.admin.tadmin.entity.TPower;
import com.cyyaw.table.admin.tadmin.entity.TRole;
import com.cyyaw.tx.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminUserServiceImpl implements AdminUserService {


    @Autowired
    private TRoleDao tRoleDao;

    @Autowired
    private TPowerDao tPowerDao;


    @Override
    public void adminMenu() {
        String adminId = "";
        // 第一步: 查询用户角色
        List<TRole> roles = tRoleDao.findByAdminId(adminId);
        // 第二步: 查询角色权限
        List<String> rolesId = roles.stream().map(TRole::getTid).collect(Collectors.toList());
        List<TPower> powers = tPowerDao.findPowerByRole(rolesId);
        // 第三步: 整理数据
        for (TPower tpower : powers) {


        }

        return;
    }


}
