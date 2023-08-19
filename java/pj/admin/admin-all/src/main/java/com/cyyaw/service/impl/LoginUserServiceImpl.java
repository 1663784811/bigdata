package com.cyyaw.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyyaw.service.LoginUserService;
import com.cyyaw.user.service.UUserService;
import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.user.utils.entity.LoginRequest;
import com.cyyaw.user.utils.entity.UserAuthToken;
import com.cyyaw.util.tools.JwtTokenUtils;
import com.cyyaw.util.tools.WhyException;
import com.cyyaw.util.tools.WhyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginUserServiceImpl implements LoginUserService {


    @Autowired
    private UUserService uUserService;

    @Override
    public UserAuthToken loginUserNameAndPassword(String enterpriseId, String userName, String password) {
        UUser user = uUserService.findByAccountAndPassword(enterpriseId, userName);
        if (null != user) {
            String pwd = user.getPassword();
            String account = user.getAccount();
            if (password.equals(pwd)) {
                // 生成Token
                String tid = user.getTid();
                String userAccount = user.getAccount();
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setId(tid);
                loginInfo.setAccount(userAccount);
                loginInfo.setUserName(user.getNickName());
                String token = JwtTokenUtils.createToken(tid, JSONUtil.toJsonStr(new JSONObject(loginInfo)));
                UserAuthToken userAuthToken = new UserAuthToken();
                userAuthToken.setJwtToken(token);
                userAuthToken.setUUser(user);
                return userAuthToken;
            } else {
                throw new WhyException("用户名或密码错误");
            }

        } else {
            throw new WhyException("找不到用户" + userName);
        }
    }

    @Override
    public UUser userRegister(LoginRequest registerInfo) {
        String enterpriseId = registerInfo.getEnterpriseId();
        String userName = registerInfo.getUserName();
        String password = registerInfo.getPassword();
        UUser uUser = uUserService.findByAccountAndPassword(enterpriseId, userName);
        if (null != uUser) {
            throw new WhyException("用户名已被注册");
        } else {
            UUser user = new UUser();
            user.setTid(WhyStringUtil.getUUID());
            user.setCreateTime(new Date());
            user.setDel(0);
            user.setNote("商城用户注册");
            user.setEnterpriseId(enterpriseId);
            user.setAccount(userName);
            user.setPassword(password);
            user.setTrueName("");
            user.setPhone("");
            user.setNickName("");
            user.setFace("");
            user.setSex("");
            user.setEmail("");
            user.setIp("");
            user.setSalt("");
            user.setStatus(0);
            user.setType(0);
            user.setAdminId("");
            user.setIntegral(0);
            user.setOpenId("");
            user.setUnionId("");
            return uUserService.save(user);
        }
    }


}
