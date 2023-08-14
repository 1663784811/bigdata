package com.cyyaw.service.impl;

import com.cyyaw.enterprise.table.dao.EStoreDao;
import com.cyyaw.store.service.GStoreGoodsSkuService;
import com.cyyaw.store.table.goods.dao.GGoodsDao;
import com.cyyaw.store.table.goods.dao.GStoreGoodsSkuDao;
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

    @Autowired
    private EStoreDao eStoreDao;

    @Autowired
    private GGoodsDao gGoodsDao;

    @Autowired
    private GStoreGoodsSkuDao gStoreGoodsSkuDao;

    @Override
    public BaseResult<List<GoodsEntity>> searchGoods(GGoodsSearch goodsSearch) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest page = PageRequest.of(0, 10, sort);

        ExampleMatcher matcher = ExampleMatcher.matching();

        Example<GGoodsSearch> example = Example.of(goodsSearch, matcher);

        Page<GGoodsSearch> goodsSearchPage = gGoodsSearchDao.findAll(example, page);

        List<GGoodsSearch> goods = goodsSearchPage.getContent();
        BaseResult.Result result = new BaseResult.Result();
        result.setPage(goodsSearchPage.getTotalPages());
        result.setSize(goodsSearchPage.getSize());
        result.setTotal(goodsSearchPage.getTotalElements());

        //  ==========================================================
        List<String> goodsId = new ArrayList<>();
        List<String> storeId = new ArrayList<>();
        for (GGoodsSearch gds : goods) {
            goodsId.add(gds.getGoodsId());
            storeId.add(gds.getStoreGoodsId());
        }
        //  ==========================================================
        //  查商品
        List<GGoods> goodsList = gGoodsDao.findByTidIn(goodsId);
        //  查门店
        List<EStore> storeList = eStoreDao.findByTidIn(storeId);
        //  查sku
        List<GStoreGoodsSku> goodsSkuList = gStoreGoodsSkuDao.findAllByGoodsId(goodsId);

        // ======================  整理数据
        List<GoodsEntity> objList = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            GGoodsSearch gGoodsSearch = goods.get(i);
            String gId = gGoodsSearch.getGoodsId();
            String sId = gGoodsSearch.getStoreGoodsId();

            GoodsEntity obj = new GoodsEntity();
            obj.setGoodsSearch(gGoodsSearch);
            // =============
            GGoods gGoods = new GGoods();
            for (int j = 0; j < goodsList.size(); j++) {
                GGoods gs = goodsList.get(j);
                if (gs.getTid().equals(gId)) {
                    gGoods = gs;
                    break;
                }
            }
            obj.setGGoods(gGoods);
            // =============
            EStore eStore = new EStore();
            for (int j = 0; j < storeList.size(); j++) {
                EStore store = storeList.get(j);
                if (store.getTid().equals(sId)) {
                    eStore = store;
                    break;
                }
            }
            obj.setEStore(eStore);
            // =============
            GStoreGoodsSku sku = new GStoreGoodsSku();
            List<GStoreGoodsSku> skuList = new ArrayList<>();
            for (int j = 0; j < goodsSkuList.size(); j++) {
                GStoreGoodsSku goodsSku = goodsSkuList.get(j);
                if (goodsSku.getGoodsId().equals(gId)) {
                    sku = goodsSku;
                    skuList.add(goodsSku);
                }
            }
            obj.setGStoreGoodsSku(sku);
            obj.setGStoreGoodsSkuList(skuList);
            objList.add(obj);
        }


        return BaseResult.ok(objList, result);
    }

    @Override
    public BaseResult goodsDetails(String goodsId) {


        return BaseResult.ok();
    }
}
