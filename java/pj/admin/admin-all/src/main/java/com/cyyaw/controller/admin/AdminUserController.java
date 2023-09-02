package com.cyyaw.controller.admin;

import com.cyyaw.service.AdminUserService;
import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.user.service.TAdminService;
import com.cyyaw.user.table.entity.TPower;
import com.cyyaw.user.utils.entity.AdminAuthToken;
import com.cyyaw.user.utils.entity.TreeEntity;
import com.cyyaw.util.tools.BaseResult;
import com.google.common.collect.Lists;
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

    @Autowired
    private TAdminService tAdminService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = "/info")
    public BaseResult info(@TokenData LoginInfo loginInfo) {
//        AdminAuthToken authToken = getAdminInfo();
        String adminId = loginInfo.getId();
//        tAdminService.findById(j);
        AdminAuthToken authToken = new AdminAuthToken();
        authToken.setJwtToken("");
//        authToken.setTAdmin();
        authToken.setAuthList(Lists.newArrayList());
        authToken.setRoleList(Lists.newArrayList());
        return BaseResult.ok(authToken);
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
