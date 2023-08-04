package com.cyyaw.service.impl;
import com.cyyaw.store.table.goods.entity.GStoreGoodsSku;
import com.google.common.collect.Lists;
import com.cyyaw.enterprise.table.entity.EStore;
import com.cyyaw.store.table.goods.entity.GGoods;

import com.cyyaw.service.ShoppingGoodsService;
import com.cyyaw.store.table.goods.dao.GGoodsSearchDao;
import com.cyyaw.store.table.goods.entity.GGoodsSearch;
import com.cyyaw.util.entity.GoodsEntity;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingGoodsServiceImpl implements ShoppingGoodsService {


    @Autowired
    private GGoodsSearchDao gGoodsSearchDao;


    @Override
    public BaseResult<List<GoodsEntity>> searchGoods(GGoodsSearch goodsSearch) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest page = PageRequest.of(1, 10, sort);

        ExampleMatcher matcher = ExampleMatcher.matching();

        Example<GGoodsSearch> example = Example.of(goodsSearch, matcher);

        Page<GGoodsSearch> goodsSearchPage = gGoodsSearchDao.findAll(example, page);

        List<GGoodsSearch> goods = goodsSearchPage.getContent();
        BaseResult.Result result = new BaseResult.Result();
        result.setPage(goodsSearchPage.getTotalPages());
        result.setSize(goodsSearchPage.getSize());
        result.setTotal(goodsSearchPage.getTotalElements());

        List<GoodsEntity> objList = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            GGoodsSearch gGoodsSearch = goods.get(i);

            GoodsEntity obj = new GoodsEntity();
            obj.setGoodsSearch(gGoodsSearch);

            obj.setGGoods(new GGoods());
            obj.setGStoreGoodsSku(new GStoreGoodsSku());
            obj.setGStoreGoodsSkuList(Lists.newArrayList());
            obj.setEStore(new EStore());

            objList.add(obj);
        }
        //  ==========================================================
        //  查门店

        //  查sku

        //  查商品


        return BaseResult.ok(objList, result);
    }

    @Override
    public BaseResult goodsDetails(String goodsId) {


        return BaseResult.ok();
    }
}
