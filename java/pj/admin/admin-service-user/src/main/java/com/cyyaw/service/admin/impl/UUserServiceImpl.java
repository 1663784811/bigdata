package com.cyyaw.service.admin.impl;


import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.cyyaw.service.admin.UUserService;
import com.cyyaw.table.admin.dao.UUserDao;
import com.cyyaw.table.admin.entity.UUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UUserServiceImpl implements UUserService {

    @Autowired
    private UUserDao uUserDao;

    @Override
    public UUser save(UUser uUser) {
        return uUserDao.save(uUser);
    }

    @Override
    public UUser findByAccountAndPassword(String account, String password) {
        List<UUser> uUsers = uUserDao.findByAccount(account);
        if (uUsers.size() == 1) {
            UUser uUser = uUsers.get(0);
            String pwd = uUser.getPassword();
            if (pwd.equals(password)) {
                return uUser;
            }
        } else {

        }
        return null;
    }

    @Override
    public UUser findByAccount(String account) {
        List<UUser> userList = uUserDao.findByAccount(account);
        if (userList.size() == 1) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public UUser upateUserByWxMaUserInfo(WxMaUserInfo wxMaUserInfo) {
        String openId = wxMaUserInfo.getOpenId();
        List<UUser> uUserList = uUserDao.findByOpenId(openId);
        UUser user = null;
        if (uUserList != null && uUserList.size() > 0) {
            user = uUserList.get(0);
        }
        user.setFace(wxMaUserInfo.getAvatarUrl());
        user.setNickName(wxMaUserInfo.getNickName());
        user.setSex(wxMaUserInfo.getGender());
        return uUserDao.save(user);
    }
}

