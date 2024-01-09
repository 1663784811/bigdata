package com.cyyaw.app.controller;


import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.service.TAdminService;
import com.cyyaw.user.service.UUserService;
import com.cyyaw.user.table.entity.TAdmin;
import com.cyyaw.user.table.entity.UUser;
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
@Api(tags = "app后台用户")
@RequestMapping("/app/{appId}/user")
@RestController
public class AppUserController {

    @Autowired
    private TAdminService tAdminService;

    @Autowired
    private UUserService uUserService;


    @ApiOperation(value = "app后台用户信息", notes = "app后台用户信息")
    @GetMapping("/userInfo")
    public BaseResult userInfo(@TokenData LoginInfo loginInfo) {
        Integer type = loginInfo.getType();
        if (null != type && type.equals(1)) {
            String enterpriseCode = loginInfo.getEnterpriseCode();
            String account = loginInfo.getAccount();
            TAdmin admin = tAdminService.findByAccount(enterpriseCode, account);
            return BaseResult.ok(admin);
        } else {
            String userId = loginInfo.getId();
            UUser user = uUserService.findByTid(userId);
            return BaseResult.ok(user);
        }
    }


}
