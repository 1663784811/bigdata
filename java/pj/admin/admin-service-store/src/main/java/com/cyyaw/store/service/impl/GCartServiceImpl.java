package com.cyyaw.store.service.impl;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.store.service.GCartService;
import com.cyyaw.store.table.goods.dao.GCartDao;
import com.cyyaw.store.table.goods.entity.GCart;
import com.cyyaw.util.tools.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GCartServiceImpl extends BaseService<GCart, Integer> implements GCartService {

    @Autowired
    private GCartDao gCarDao;

    @Override
    public BaseDao getBaseDao() {
        return gCarDao;
    }

    @Override
    public BaseResult myCartList() {

        //  第一步: 根据门店分组，查出有多少门店
        gCarDao.findAll();

//        CSql cSql = new CSql();
//        ExampleMatcher matcher = ExampleMatcher.matching();
//        PageRequest of = PageRequest.of(page - 1, size);
//        Example<CSql> ex = Example.of(cSql, matcher);
//        Page<CSql> sqlPage = cSqlDao.findAll(ex, of);

        // 第二步: 查询购物车里的商品(sku)

        //


        return BaseResult.ok();
    }
}

