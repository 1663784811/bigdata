package com.cyyaw.appadmin.controller;


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
@Api(tags = "商城用户")
@RequestMapping("/appAdmin/{appId}/shopping/user")
@RestController
public class ShoppingUserController {


    @Autowired
    private UUserService uUserService;

    @Autowired
    private UAddressService uAddressService;

    @ApiOperation(value = "用户地址列表", notes = "用户地址列表")
    @GetMapping("/address")
    public BaseResult address(@TokenData LoginInfo loginInfo) {
        String userId = loginInfo.getId();
        return uAddressService.findUserAddress(userId);
    }

    @ApiOperation(value = "用户地址详情", notes = "用户地址详情")
    @GetMapping("/addressDetail")
    public BaseResult addressDetail(Integer id) {
        UAddress address = uAddressService.findId(id);
        return BaseResult.ok(address);
    }

    @ApiOperation(value = "获取默认地址", notes = "获取默认地址")
    @GetMapping("/defaultAddress")
    public BaseResult defaultAddress(String addressId, @TokenData LoginInfo loginInfo) {
        String userId = loginInfo.getId();
        UAddress address = uAddressService.defaultAddress(userId, addressId);
        return BaseResult.ok(address);
    }


}
