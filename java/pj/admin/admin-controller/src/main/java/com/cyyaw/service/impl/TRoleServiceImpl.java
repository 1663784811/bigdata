//package com.cyyaw.service.impl;
//
//import com.cyyaw.jpa.BaseDao;
//import com.cyyaw.jpa.BaseService;
//import com.cyyaw.table.admin.tadmin.TRole;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//@Slf4j
//public class TRoleServiceImpl extends BaseService<TRole, Integer> implements TRoleService {
//
//    @Autowired
//    private TRoleDao tRoleDao;
//
//    @Override
//    public BaseDao getBaseDao() {
//        return tRoleDao;
//    }
//
//    @Override
//    public List<TRole> findTRoleByAdminId(String adminId) {
//
//        return tRoleDao.findByAdminId(adminId);
//    }
//}
//
