package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.UAddressService;
import com.cyyaw.config.table.table.dao.user.UAddressDao;
import com.cyyaw.config.table.table.entity.user.UAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
public class UAddressServiceImpl extends BaseService<UAddress, Integer> implements UAddressService {

    @Autowired
    private UAddressDao uAddressDao;

    @Override
    public BaseDao getBaseDao() {
        return uAddressDao;
    }

    @Override
    public List<UAddress> findByUid(String uid) {

        return uAddressDao.findAll(uid);
    }
}

