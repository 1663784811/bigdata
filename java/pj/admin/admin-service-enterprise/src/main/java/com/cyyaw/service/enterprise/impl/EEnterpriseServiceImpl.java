package com.cyyaw.service.enterprise.impl;

import com.cyyaw.service.enterprise.EEnterpriseService;
import com.cyyaw.table.enterprise.dao.EEnterpriseDao;
import com.cyyaw.table.enterprise.entity.EEnterprise;
import com.cyyaw.util.tools.WhyStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Slf4j
public class EEnterpriseServiceImpl implements EEnterpriseService {

    @Autowired
    private EEnterpriseDao eEnterpriseDao;

    /**
     * 注册企业
     */
    @Override
    public EEnterprise save(EEnterprise enterprise) {
        Integer id = enterprise.getId();
        if (null != id) {
            EEnterprise eEnterprise = eEnterpriseDao.findByid(id);
            BeanUtils.copyProperties(enterprise, eEnterprise);
            return eEnterpriseDao.save(eEnterprise);
        } else {
            enterprise.setTid(WhyStringUtil.getUUID());
            enterprise.setCreateTime(new Date());
            return eEnterpriseDao.save(enterprise);
        }

    }

    @Override
    public EEnterprise registerEnterprise(EEnterprise enterprise) {
        return save(enterprise);
    }
}

