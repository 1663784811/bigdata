package com.cyyaw.store.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.store.service.GGoodsService;
import com.cyyaw.store.table.goods.dao.GGoodsDao;
import com.cyyaw.store.table.goods.entity.GGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class GGoodsServiceImpl extends BaseService<GGoods, Integer> implements GGoodsService {

    @Autowired
    private GGoodsDao gGoodsDao;

    @Override
    public BaseDao getBaseDao() {
        return gGoodsDao;
    }

    @Override
    public GGoods findByTid(String tid) {
        return gGoodsDao.findByTid(tid);
    }
}

