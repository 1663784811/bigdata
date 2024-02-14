package com.cyyaw.app.controller.user;


import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.service.UAddressService;
import com.cyyaw.user.service.UUserService;
import com.cyyaw.user.table.entity.UAddress;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "用户管理")
@RequestMapping("/app/{appId}/address")
@RestController
public class AppAddressController {


    @Autowired
    private UUserService uUserService;

    @Autowired
    private UAddressService uAddressService;




}
