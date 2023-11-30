package com.cyyaw.controller.login;


import com.cyyaw.service.LoginService;
import com.cyyaw.user.table.entity.TAdmin;
import com.cyyaw.user.utils.entity.AdminAuthToken;
import com.cyyaw.user.utils.entity.LoginRequest;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "APP管理员登录模块")
@RequestMapping("/app/{appId}/admin/login")
@RestController
public class AppAdminLoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping(value = "/logout")
    public BaseResult logout() {
        return BaseResult.ok("退出登录成功");
    }

    @ApiOperation(value = "app管理员登录", notes = "app管理员登录")
    @PostMapping(value = "/adminLogin")
    public BaseResult adminLogin(@RequestBody LoginRequest loginRequest) {
        String appId = loginRequest.getAppId();
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        AdminAuthToken authToken = loginService.adminLogin(appId, userName, password);
        TAdmin tAdmin = authToken.getTAdmin();
        tAdmin.setPassword(null);
        return BaseResult.ok(authToken, "登录成功");
    }

    @ApiOperation(value = "app管理员验证码登录", notes = "app管理员验证码登录")
    @PostMapping(value = "/userLogin")
    public BaseResult phoneLogin(@RequestBody LoginRequest loginRequest) {
        String appId = loginRequest.getAppId();
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        AdminAuthToken authToken = loginService.adminLogin(appId, userName, password);
        TAdmin tAdmin = authToken.getTAdmin();
        tAdmin.setPassword(null);
        return BaseResult.ok(authToken, "登录成功");
    }


}
