package com.cyyaw.service;

import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.user.UAddress;

import java.util.List;

public interface UAddressService extends BaseTableService<UAddress, Integer> {


    List<UAddress> findByUid(String uid);
}
