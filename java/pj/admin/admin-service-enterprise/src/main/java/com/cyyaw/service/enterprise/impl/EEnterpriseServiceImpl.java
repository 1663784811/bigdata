package com.cyyaw.service.enterprise.impl;

import com.cyyaw.service.enterprise.EEnterpriseService;
import com.cyyaw.table.enterprise.dao.EEnterpriseDao;
import com.cyyaw.table.enterprise.entity.EEnterprise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EEnterpriseServiceImpl implements EEnterpriseService {

    @Autowired
    private EEnterpriseDao eEnterpriseDao;

    /**
     * 注册企业
     *
     * @param enterprise
     * @return
     */
    @Override
    public EEnterprise registerEnterprise(EEnterprise enterprise) {
        // 第一步: 注册企业
        EEnterprise save = eEnterpriseDao.save(enterprise);
        return save;
    }
}

