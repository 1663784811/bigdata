package com.cyyaw.app.controller.friends;


import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.service.UUserService;
import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "APP-好友")
@RequestMapping("/app/{appId}/friends")
@RestController
public class FriendsController {


    @Autowired
    private UUserService userService;




    @ApiOperation(value = "获取我的好友列表", notes = "获取我的好友列表")
    @GetMapping(value = "/myFriends")
    public BaseResult myFriends(@TokenData LoginInfo loginInfo, @PathVariable String appId) {
        String uid = loginInfo.getId();
        return BaseResult.ok(userService.myFriends(uid, appId));
    }


}
