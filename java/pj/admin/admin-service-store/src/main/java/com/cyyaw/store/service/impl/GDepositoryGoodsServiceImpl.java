package com.cyyaw.store.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.store.service.GDepositoryGoodsService;
import com.cyyaw.store.table.goods.dao.GDepositoryGoodsDao;
import com.cyyaw.store.table.goods.entity.GDepositoryGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class GDepositoryGoodsServiceImpl extends BaseService<GDepositoryGoods, Integer> implements GDepositoryGoodsService {

    @Autowired
    private GDepositoryGoodsDao gDepositoryGoodsDao;

    @Override
    public BaseDao getBaseDao() {
        return gDepositoryGoodsDao;
    }

}

