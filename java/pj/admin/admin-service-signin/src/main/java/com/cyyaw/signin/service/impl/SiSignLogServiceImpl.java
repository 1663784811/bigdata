package com.cyyaw.signin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.signin.service.SiSignLogService;
import com.cyyaw.signin.table.dao.SiSignLogDao;
import com.cyyaw.signin.table.entity.SiSignLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class SiSignLogServiceImpl extends BaseService<SiSignLog, Integer> implements SiSignLogService {

    @Autowired
    private SiSignLogDao siSignLogDao;

    @Override
    public BaseDao getBaseDao() {
        return siSignLogDao;
    }

    @Override
    public List<SiSignLog> findBySignInId(String signInId) {
        if (StrUtil.isNotBlank(signInId)) {
            SiSignLog signLog = new SiSignLog();
            signLog.setSiSignInId(signInId);
            Example<SiSignLog> example = Example.of(signLog);
            return siSignLogDao.findAll(example);
        } else {
            return new ArrayList<>();
        }
    }
}

