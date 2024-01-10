package com.cyyaw.user.service.impl;


import cn.hutool.core.util.StrUtil;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.user.service.UUserService;
import com.cyyaw.user.table.dao.UUserDao;
import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.util.tools.WhyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UUserServiceImpl extends BaseService<UUser, Integer> implements UUserService {

    @Autowired
    private UUserDao uUserDao;

    @Override
    public BaseDao getBaseDao() {
        return uUserDao;
    }

    @Override
    public UUser findByAppIdAndPassword(String appId, String account) {
        if (StrUtil.isNotBlank(appId) && StrUtil.isNotBlank(account)) {
            List<UUser> uUsers = uUserDao.findByAppIdAndAccount(appId, account);
            if (uUsers.size() == 1) {
                UUser uUser = uUsers.get(0);
                return uUser;
            } else {

            }
        } else {
            throw new WhyException("您输入的信息有误");
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
    public UUser findByTid(String tid) {
        return uUserDao.findByTid(tid);
    }

}

