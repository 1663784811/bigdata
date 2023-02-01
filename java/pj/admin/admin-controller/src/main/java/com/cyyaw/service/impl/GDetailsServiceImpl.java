package com.cyyaw.service.impl;

import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.table.service.GDetailsService;
import com.cyyaw.config.table.table.dao.goods.GDetailsDao;
import com.cyyaw.config.table.table.entity.goods.GDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
public class GDetailsServiceImpl extends BaseService<GDetails, Integer> implements GDetailsService {

    @Autowired
    private GDetailsDao gDetailsDao;

    @Override
    public BaseDao getBaseDao() {
        return gDetailsDao;
    }

    @Override
    public GDetails findBySkuId(String goodsId) {
        List<GDetails> list = gDetailsDao.findByGoodsid(goodsId);
        if(null != list && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}

