//package com.cyyaw.init;
//
//import com.cyyaw.sql.table.dao.CPageComponentsDao;
//import com.cyyaw.sql.table.dao.CPageDao;
//import com.cyyaw.table.spider.tag.dao.TagDao;
//import com.cyyaw.table.spider.tag.entity.Tag;
//import com.cyyaw.user.table.dao.*;
//import com.cyyaw.user.table.entity.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * 初始化
// */
//@Service
//public class InitApplication {
//
//    @Autowired
//    private CPageDao pageDao;
//
//    @Autowired
//    private CPageComponentsDao cPageComponentsDao;
//
//    @Autowired
//    private TagDao tagDao;
//
//
//    @Autowired
//    private InitTag initTag;
//
//
//    @Autowired
//    private TAdminDao tAdminDao;
//
//    @Autowired
//    private TRoleDao tRoleDao;
//
//    @Autowired
//    private TAdminRoleDao tAdminRoleDao;
//
//    @Autowired
//    private TPowerDao tPowerDao;
//
//    @Autowired
//    private TRolePowerDao tRolePowerDao;
//
//
//    public void init() {
////        CPageComponents components = new CPageComponents();
////        components.setId(0);
////        components.setTid("aa");
////        components.setPageId("aa");
////        components.setName("表格组件");
////        components.setComponents_code("aa");
////        components.setData("");
////        components.setSort(0);
////        cPageComponentsDao.save(components);
////        tag();
//        addAdmin();
//        addRole();
//        addPower();
//    }
//
//
//    public void tag() {
////        Tag tag = getTag(1);
////        tag.setId(0);
////        tag.setCreateTime(new Date());
////        tag.setDel(0);
////        tag.setNote("");
////        tag.setTid("");
////        tag.setName("");
////        tag.setPid("");
////        tag.setTreecode("");
////        tag.setType(0);
////
////        tagDao.save(tag);
//
//    }
//
//
//    public Tag getTag(Integer id) {
//        Tag obj = tagDao.findByid(id);
//        if (null == obj) {
//            Tag tag = new Tag();
//            tag.setId(id);
//            return tag;
//        }
//        return obj;
//    }
//
//
//    public void addAdmin() {
//        String enterpriseCode = "enterpriseCode";
//        String account = "admin";
//        TAdmin admin = tAdminDao.findByAccount(enterpriseCode, account);
//        if (null == admin) {
//            admin = new TAdmin();
//            admin.setId(0);
//            admin.setTid(account);
//            admin.setCreateTime(new Date());
//            admin.setDel(0);
//            admin.setNote("");
//            admin.setEnterpriseCode(enterpriseCode);
//            admin.setAccount(account);
//            admin.setCanLoginTime(new Date());
//            admin.setEmail("");
//            admin.setIp("127.0.0.1");
//            admin.setLastLoginTime(new Date());
//            admin.setNickName("");
//            admin.setPassword("");
//            admin.setPhone("12345678900");
//            admin.setSalt("");
//            admin.setStatus(0);
//            admin.setTrueName("");
//            tAdminDao.save(admin);
//        }
//    }
//
//
//    public void addRole() {
//        String account = "admin";
//        List<TRole> roles = tRoleDao.findByAdminId(account);
//        if (roles.size() <= 0) {
//            TRole tRole = new TRole();
//            tRole.setId(0);
//            tRole.setTid(account);
//            tRole.setCreateTime(new Date());
//            tRole.setDel(0);
//            tRole.setNote("");
//            tRole.setCode("admin");
//            tRole.setName("超级管理员");
//            tRole.setPid("");
//            tRole.setTreeCode("");
//            tRoleDao.save(tRole);
//
//            //分配权限
//            TAdminRole tAdminRole = new TAdminRole();
//            tAdminRole.setId(0);
//            tAdminRole.setTid("");
//            tAdminRole.setCreateTime(new Date());
//            tAdminRole.setDel(0);
//            tAdminRole.setNote("");
//            tAdminRole.setAdminId(account);
//            tAdminRole.setRoleId(account);
//            tAdminRoleDao.save(tAdminRole);
//        }
//    }
//
//
//    public void addPower() {
//        String account = "admin";
//        String sqlConfig = "sqlConfig";
//        List<String> list = new ArrayList<>();
//        list.add(account);
//        List<TPower> powers = tPowerDao.findPowerByRole(list);
//        if (powers.size() == 0) {
//            TPower tPower = new TPower();
//            tPower.setId(0);
//            tPower.setTid(sqlConfig);
//            tPower.setCreateTime(new Date());
//            tPower.setDel(0);
//            tPower.setNote("");
//            tPower.setPid("");
//            tPower.setTreeCode("");
//            tPower.setCode("sqlConfig");
//            tPower.setIcon("");
//            tPower.setName("Sql配置");
//            tPower.setPowerType(0);
//            tPower.setStatus(0);
//            tPower.setIsPower(0);
//            tPower.setUrl("/sql/config");
//            tPower.setSort(0);
//            tPowerDao.save(tPower);
//
//            TRolePower tRolePower = new TRolePower();
//            tRolePower.setId(0);
//            tRolePower.setTid("");
//            tRolePower.setCreateTime(new Date());
//            tRolePower.setDel(0);
//            tRolePower.setNote("");
//            tRolePower.setPowerId(sqlConfig);
//            tRolePower.setRoleId(account);
//            tRolePowerDao.save(tRolePower);
//        }
//
//
//    }
//
//}
