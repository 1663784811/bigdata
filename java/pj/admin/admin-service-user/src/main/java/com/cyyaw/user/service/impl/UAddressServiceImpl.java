package com.cyyaw.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.user.service.UAddressService;
import com.cyyaw.user.table.dao.UAddressDao;
import com.cyyaw.user.table.entity.UAddress;
import com.cyyaw.util.tools.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    public BaseResult findUserAddress(String userId) {
        PageRequest pageRequest = PageRequest.of(0, 20);
        UAddress address = new UAddress();
        address.setUserId(userId);
        Example<UAddress> example = Example.of(address);
        Page<UAddress> pageRest = uAddressDao.findAll(example, pageRequest);
        List<UAddress> data = pageRest.getContent();
        BaseResult.Result result = new BaseResult.Result();
        result.setPage(pageRest.getTotalPages());
        result.setSize(pageRest.getSize());
        result.setTotal(pageRest.getTotalElements());
        // ==============================================
        return BaseResult.ok(data, result);
    }

    @Override
    public UAddress defaultAddress(String userId, String addressId) {
        UAddress address = new UAddress();
        address.setUserId(userId);
        if (StrUtil.isNotBlank(addressId)) {
            address.setTid(addressId);
        } else {
            address.setDefaultIs(1);
        }
        Example<UAddress> example = Example.of(address);
        List<UAddress> all = uAddressDao.findAll(example);
        if (all != null && all.size() > 0) {
            return all.get(0);
        }
        return null;
    }
}

