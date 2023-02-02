package com.cyyaw.tx.web.service.impl;


import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.table.store.goods.GGoodsSearch;
import com.cyyaw.tx.web.service.GGoodsSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class GGoodsSearchServiceImpl extends BaseService<GGoodsSearch, Integer> implements GGoodsSearchService {

    @Autowired
    private GGoodsSearchDao gGoodsSearchDao;

    @Override
    public BaseDao getBaseDao() {
        return gGoodsSearchDao;
    }

}

