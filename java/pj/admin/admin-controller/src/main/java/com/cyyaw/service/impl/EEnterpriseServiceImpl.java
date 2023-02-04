//package com.cyyaw.service.impl;
//
//import com.cyyaw.jpa.BaseDao;
//import com.cyyaw.jpa.BaseService;
//import com.cyyaw.table.admin.enterprise.entity.EEnterprise;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//@Slf4j
//public class EEnterpriseServiceImpl extends BaseService<EEnterprise, Integer> implements EEnterpriseService {
//
//    @Autowired
//    private EEnterpriseDao eEnterpriseDao;
//
//    @Override
//    public BaseDao getBaseDao() {
//        return eEnterpriseDao;
//    }
//
//    /**
//     * 注册企业
//     * @param enterprise
//     * @return
//     */
//    @Override
//    public EEnterprise registerEnterprise(EEnterprise enterprise) {
//        // 第一步: 注册企业
//        EEnterprise save = eEnterpriseDao.save(enterprise);
//
//
//        return save;
//    }
//}
//
