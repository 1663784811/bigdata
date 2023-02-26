package com.cyyaw.tx.admin.controller;

import com.cyyaw.entity.TreeEntity;
import com.cyyaw.service.admin.AdminUserService;
import com.cyyaw.table.admin.entity.TPower;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/admin/user")
@RestController
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = "/info")
    public BaseResult info() {
//        AdminAuthToken authToken = getAdminInfo();
//        return BaseResult.ok(authToken);
        return BaseResult.ok();
    }


    /**
     * 获取用户菜单
     *
     * @return
     */
    @GetMapping(value = "/adminMenu")
    public BaseResult adminMenu() {
        String adminId = "admin";
        List<TreeEntity.Node<TPower>> arr = adminUserService.adminMenu(adminId);
        return BaseResult.ok(arr);
    }
















}
