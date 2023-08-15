package com.cyyaw.tx;


import com.cyyaw.service.LoginUserService;
import com.cyyaw.user.service.UUserService;
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
@Api(tags = "商城用户")
@RequestMapping("/shopping/user")
@RestController
public class ShoppingUser {


    @Autowired
    private UUserService uUserService;

    @ApiOperation(value = "商城用户信息", notes = "商城用户信息")
    @GetMapping("/userInfo")
    public BaseResult userInfo(LoginRequest loginRequest) {
        UUser user = uUserService.findByAccount("root");
        return BaseResult.ok(user);
    }


}
