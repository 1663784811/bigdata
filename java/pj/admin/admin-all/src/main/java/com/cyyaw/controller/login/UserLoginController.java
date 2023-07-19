package com.cyyaw.controller.login;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "用户登录模块")
@RequestMapping("/login/user")
@RestController
public class UserLoginController {



    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping(value = "/login")
    public void login(){


    }


    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/register")
    public void register(){


    }

}
