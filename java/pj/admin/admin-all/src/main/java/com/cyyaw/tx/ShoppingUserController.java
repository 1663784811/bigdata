package com.cyyaw.tx;


import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.service.UAddressService;
import com.cyyaw.user.service.UUserService;
import com.cyyaw.user.table.entity.UAddress;
import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.user.utils.entity.LoginRequest;
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
public class ShoppingUserController {


    @Autowired
    private UUserService uUserService;


    @Autowired
    private UAddressService uAddressService;


    @ApiOperation(value = "商城用户信息", notes = "商城用户信息")
    @GetMapping("/userInfo")
    public BaseResult userInfo(@TokenData LoginInfo loginInfo) {
        String userId = loginInfo.getId();
        UUser user = uUserService.findByTid(userId);
        return BaseResult.ok(user);
    }


    @ApiOperation(value = "用户地址列表", notes = "用户地址列表")
    @GetMapping("/address")
    public BaseResult address(LoginRequest loginRequest) {
        return uAddressService.findUserAddress();
    }

    @ApiOperation(value = "用户地址详情", notes = "用户地址详情")
    @GetMapping("/addressDetail")
    public BaseResult addressDetail(Integer id) {
        UAddress address = uAddressService.findId(id);
        return BaseResult.ok(address);
    }


}
