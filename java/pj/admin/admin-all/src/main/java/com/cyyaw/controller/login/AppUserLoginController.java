package com.cyyaw.controller.login;


import com.cyyaw.service.LoginUserService;
import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.user.utils.entity.LoginRequest;
import com.cyyaw.user.utils.entity.UserAuthToken;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "APP用户登录")
@RequestMapping("/app/{appId}/user/login")
@RestController
public class AppUserLoginController {


    @Autowired
    private LoginUserService loginUserService;


    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping(value = "/logout")
    public BaseResult logout() {
        return BaseResult.ok("退出登录成功");
    }


    @ApiOperation(value = "APP用户登录", notes = "APP用户登录")
    @PostMapping("login")
    public BaseResult login(@RequestBody LoginRequest loginRequest, @PathVariable String appId) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        UserAuthToken authToken = loginUserService.loginUserNameAndPassword(appId, userName, password);
        UUser uUser = authToken.getUUser();
        uUser.setPassword(null);
        return BaseResult.ok(authToken, "登录成功");
    }


    @ApiOperation(value = "APP用户手机验证码登录", notes = "APP用户手机验证码登录")
    @PostMapping("phoneLogin")
    public BaseResult phoneLogin(@RequestBody LoginRequest loginRequest, @PathVariable String appId) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        UserAuthToken authToken = loginUserService.loginUserNameAndPassword(appId, userName, password);
        UUser uUser = authToken.getUUser();
        uUser.setPassword(null);
        return BaseResult.ok(authToken, "登录成功");
    }


    @ApiOperation(value = "APP用户微信登录", notes = "APP用户手机验证码登录")
    @PostMapping("weixinLogin")
    public BaseResult weixinLogin(@RequestBody LoginRequest loginRequest, @PathVariable String appId) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        UserAuthToken authToken = loginUserService.loginUserNameAndPassword(appId, userName, password);
        UUser uUser = authToken.getUUser();
        uUser.setPassword(null);
        return BaseResult.ok(authToken, "登录成功");
    }


    @ApiOperation(value = "APP用户注册", notes = "APP用户注册")
    @PostMapping(value = "/register")
    public BaseResult register(@RequestBody LoginRequest registerInfo, @PathVariable String appId) {
        UUser user = loginUserService.userRegister(appId, registerInfo);
        user.setPassword(null);
        return BaseResult.ok(user, "注册成功");
    }


}
