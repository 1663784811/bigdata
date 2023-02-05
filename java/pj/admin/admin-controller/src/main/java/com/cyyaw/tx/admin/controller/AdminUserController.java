package com.cyyaw.tx.admin.controller;

import com.cyyaw.util.tools.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/user")
@RestController()
public class AdminUserController {

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
     */
    public void aaa(){


    }

}
