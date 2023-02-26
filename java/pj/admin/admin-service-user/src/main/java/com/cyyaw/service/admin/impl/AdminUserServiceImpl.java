package com.cyyaw.service.admin.impl;

import com.cyyaw.entity.TreeEntity;
import com.cyyaw.service.admin.AdminUserService;
import com.cyyaw.table.admin.dao.TPowerDao;
import com.cyyaw.table.admin.dao.TRoleDao;
import com.cyyaw.table.admin.entity.TPower;
import com.cyyaw.table.admin.entity.TRole;
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
    public List<TreeEntity.Node<TPower>> adminMenu(String adminId) {
        // 第一步: 查询用户角色
        List<TRole> roles = tRoleDao.findByAdminId(adminId);
        // 第二步: 查询角色权限
        List<String> rolesId = roles.stream().map(TRole::getTid).collect(Collectors.toList());
        List<TPower> powers = tPowerDao.findPowerByRole(rolesId);
        // 第三步: 整理数据
        TreeEntity treeEntity = new TreeEntity();
        for (TPower tpower : powers) {
            TreeEntity.Node<TPower> node = new TreeEntity.Node<TPower>();
            node.setTid(tpower.getTid());
            node.setData(tpower);
            node.setName(tpower.getName());
            node.setPid(tpower.getPid());
            treeEntity.add(node);
        }
        return treeEntity.getRoot();
    }


}
