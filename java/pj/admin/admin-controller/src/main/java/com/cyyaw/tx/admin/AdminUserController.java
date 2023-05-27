package com.cyyaw.tx.admin;

import com.cyyaw.entity.AdminAuthToken;
import com.cyyaw.entity.LoginInfo;
import com.cyyaw.entity.TreeEntity;
import com.cyyaw.service.admin.AdminUserService;
import com.cyyaw.service.admin.TAdminService;
import com.cyyaw.table.admin.entity.TPower;
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
    public BaseResult info(LoginInfo loginInfo) {
//        AdminAuthToken authToken = getAdminInfo();
        String adminId = loginInfo.getTid();
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
