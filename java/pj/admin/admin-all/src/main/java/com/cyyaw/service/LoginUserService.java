package com.cyyaw.service;

import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.user.utils.entity.LoginRequest;
import com.cyyaw.user.utils.entity.UserAuthToken;

public interface LoginUserService {


    // APP登录
    UserAuthToken loginUserNameAndPassword(String appId, String userName, String password);


    UUser userRegister(String appId, LoginRequest registerInfo);


}
