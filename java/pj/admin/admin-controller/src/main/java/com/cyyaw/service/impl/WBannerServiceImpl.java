package com.cyyaw.service.impl;


import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.WBannerService;
import com.cyyaw.config.table.table.dao.WBannerDao;
import com.cyyaw.config.table.table.entity.WBanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class WBannerServiceImpl extends BaseService<WBanner, Integer> implements WBannerService {

    @Autowired
    private WBannerDao wBannerDao;

    @Override
    public BaseDao getBaseDao() {
        return wBannerDao;
    }

}

