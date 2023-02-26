package com.cyyaw.service.admin.impl;

import com.cyyaw.entity.tablecode.RoleCode;
import com.cyyaw.service.admin.TPowerService;
import com.cyyaw.service.admin.TRoleService;
import com.cyyaw.table.admin.dao.TAdminRoleDao;
import com.cyyaw.table.admin.dao.TRoleDao;
import com.cyyaw.table.admin.entity.TAdminRole;
import com.cyyaw.table.admin.entity.TPower;
import com.cyyaw.table.admin.entity.TRole;
import com.cyyaw.util.tools.WhyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TRoleServiceImpl implements TRoleService {

    @Autowired
    private TPowerService tPowerService;


    @Autowired
    private TRoleDao tRoleDao;

    @Autowired
    private TAdminRoleDao tAdminRoleDao;


    @Override
    public TRole save(TRole tRole) {


        tRole.setTid(WhyStringUtil.getUUID());
        tRole.setCreateTime(new Date());
        tRole.setDel(0);
        tRole.setNote("");

        return tRoleDao.save(tRole);
    }

    @Override
    public void initRole(String enterpriseId, String adminId) {
        // 初始化菜单
        List<TPower> tPowers = tPowerService.initPower(enterpriseId);
        // 初始化角色
        TRole tRole = new TRole();
        tRole.setCode(RoleCode.admin.getCode());
        tRole.setName("负责人");
        tRole.setEnterpriseId(enterpriseId);
        TRole save = this.save(tRole);
        String roleId = save.getTid();
        // 绑定菜单


        // 绑定角色
        binRole(adminId, roleId);
    }

    public TAdminRole binRole(String adminId, String roleId) {
        List<TAdminRole> roles = tAdminRoleDao.findByAdminIdAndRoleID(adminId, roleId);
        if (roles.size() > 0) {
            return roles.get(0);
        } else {
            TAdminRole obj = new TAdminRole();
            obj.setTid(WhyStringUtil.getUUID());
            obj.setCreateTime(new Date());
            obj.setDel(0);
            obj.setNote("");
            obj.setAdminId(adminId);
            obj.setRoleId(roleId);
            return tAdminRoleDao.save(obj);
        }
    }


}
