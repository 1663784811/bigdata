package com.cyyaw.user.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.user.service.UAddressService;
import com.cyyaw.user.table.dao.UAddressDao;
import com.cyyaw.user.table.entity.UAddress;
import com.cyyaw.util.tools.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UAddressServiceImpl extends BaseService<UAddress, Integer> implements UAddressService {

    @Autowired
    private UAddressDao uAddressDao;

    @Override
    public BaseDao getBaseDao() {
        return uAddressDao;
    }

    @Override
    public BaseResult findUserAddress() {

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<UAddress> pageRest = uAddressDao.findAll(pageRequest);
        List<UAddress> data = pageRest.getContent();

        BaseResult.Result result = new BaseResult.Result();
        result.setPage(pageRest.getTotalPages());
        result.setSize(pageRest.getSize());
        result.setTotal(pageRest.getTotalElements());

        // ==============================================


        return BaseResult.ok(data, result);
    }
}

