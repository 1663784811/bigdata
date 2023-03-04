package com.cyyaw.service.admin.impl;

import com.cyyaw.service.admin.TAdminService;
import com.cyyaw.table.admin.dao.TAdminDao;
import com.cyyaw.table.admin.entity.TAdmin;
import com.cyyaw.util.tools.WhyStringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TAdminServiceImpl implements TAdminService {

    @Autowired
    private TAdminDao tAdminDao;

    @Override
    public TAdmin findById(Integer id) {
        return tAdminDao.findByid(id);
    }

    @Override
    public TAdmin findByTid(String tid) {
//        return tAdminDao;
        return null;
    }

    @Override
    public TAdmin save(TAdmin admin) {
        Integer id = admin.getId();
        if (null != id) {
            TAdmin tAdmin = tAdminDao.findByid(id);
            BeanUtils.copyProperties(admin, tAdmin);
            return tAdminDao.save(tAdmin);
        } else {
            admin.setTid(WhyStringUtil.getUUID());
            admin.setCreateTime(new Date());
            admin.setDel(0);
            return tAdminDao.save(admin);
        }
    }
}
