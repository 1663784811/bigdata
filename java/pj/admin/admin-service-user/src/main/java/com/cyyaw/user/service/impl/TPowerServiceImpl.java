package com.cyyaw.user.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.user.service.TPowerService;
import com.cyyaw.user.table.dao.TPowerDao;
import com.cyyaw.user.table.entity.TPower;
import com.cyyaw.user.utils.PowerCode;
import com.cyyaw.util.tools.WhyStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TPowerServiceImpl extends BaseService<TPower, Integer> implements TPowerService {

    @Autowired
    private TPowerDao tPowerDao;

    @Override
    public BaseDao getBaseDao() {
        return tPowerDao;
    }

    @Override
    public List<TPower> findAdminPower(String tid) {
        return tPowerDao.findAdminPower(tid);
    }

    @Override
    public List<TPower> initPower(String enterpriseId) {
        PowerCode[] powerCodes = PowerCode.values();
        List<TPower> arr = new ArrayList<>();
        for (int i = 0; i < powerCodes.length; i++) {
            PowerCode powerCode = powerCodes[i];
            String code = powerCode.getCode();

            TPower tPower = new TPower();
            tPower.setTid(WhyStringUtil.getUUID());
            tPower.setPid("");
            tPower.setTreeCode("");
            tPower.setCode("");
            tPower.setIcon("");
            tPower.setName("");
            tPower.setPowerType(0);
            tPower.setStatus(0);
            tPower.setIsPower(0);
            tPower.setUrl("");
            tPower.setSort(0);
            tPower.setEnterpriseId(enterpriseId);
            TPower save = tPowerDao.save(tPower);
            arr.add(save);
        }
        return arr;
    }
}
