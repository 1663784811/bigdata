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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "商城登录")
@RequestMapping("/login/shopping")
@RestController
public class ShoppingLogin {


    @Autowired
    private LoginUserService loginUserService;

    @ApiOperation(value = "商城登录", notes = "商城登录")
    @PostMapping("login")
    public BaseResult login(@RequestBody LoginRequest loginRequest){
        String enterpriseId = loginRequest.getEnterpriseId();
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        UserAuthToken authToken = loginUserService.loginUserNameAndPassword(enterpriseId, userName, password);
        UUser uUser = authToken.getUUser();
        uUser.setPassword(null);
        return BaseResult.ok(authToken, "登录成功");
    }


    @ApiOperation(value = "商城注册", notes = "商城注册")
    @PostMapping(value = "/register")
    public BaseResult register(@RequestBody LoginRequest registerInfo) {
        UUser user = loginUserService.userRegister(registerInfo);
        user.setPassword(null);
        return BaseResult.ok(user, "注册成功");
    }



}
