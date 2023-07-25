package com.cyyaw.service;

import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.user.utils.entity.AdminAuthToken;
import com.cyyaw.user.utils.entity.LoginRequest;
import com.cyyaw.user.utils.entity.UserAuthToken;

public interface LoginUserService {


    UserAuthToken loginUserNameAndPassword(String enterpriseId, String userName, String password);


    UUser userRegister(LoginRequest registerInfo);


}
