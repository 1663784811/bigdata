package com.cyyaw.tx.login;


import com.cyyaw.entity.AdminAuthToken;
import com.cyyaw.entity.LoginRequest;
import com.cyyaw.table.admin.tadmin.TAdmin;
import com.cyyaw.table.admin.tadmin.TPower;
import com.cyyaw.tx.admin.service.TPowerService;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.tx.web.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TPowerService tPowerService;

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping(value = "/logout")
    public BaseResult logout() {
        return BaseResult.ok("退出登录成功");
    }

    @ApiOperation(value = "后台登录", notes = "后台登录")
    @PostMapping(value = "/admin/login")
    public BaseResult login(@RequestBody LoginRequest loginRequest) {
        AdminAuthToken authToken = loginService.loginUserNameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
        TAdmin tAdmin = authToken.getTAdmin();
        tAdmin.setPassword(null);
        List<TPower> tPowerList = tPowerService.findAdminPower(tAdmin.getTid());
        authToken.setAuthList(tPowerList);
        return BaseResult.ok(authToken, "登录成功");
    }

    @ApiOperation(value = "管理员注册", notes = "用户注册")
    @PostMapping(value = "/admin/register")
    public BaseResult register(@RequestBody LoginRequest registerInfo) {
        TAdmin tAdmin = loginService.adminRegister(registerInfo);
        tAdmin.setPassword(null);
        return BaseResult.ok(tAdmin, "注册成功");
    }

}
