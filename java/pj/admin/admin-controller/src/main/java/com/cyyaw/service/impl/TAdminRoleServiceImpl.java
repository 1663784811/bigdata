package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.TAdminRoleService;
import com.cyyaw.config.table.table.dao.tadmin.TAdminRoleDao;
import com.cyyaw.config.table.table.entity.tadmin.TAdminRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TAdminRoleServiceImpl extends BaseService<TAdminRole, Integer> implements TAdminRoleService {

    @Autowired
    private TAdminRoleDao tAdminRoleDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminRoleDao;
    }

}

