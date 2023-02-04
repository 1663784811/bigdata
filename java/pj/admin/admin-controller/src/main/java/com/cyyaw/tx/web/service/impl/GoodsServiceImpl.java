package com.cyyaw.tx.web.service.impl;

import com.cyyaw.entity.GoodsEntity;
import com.cyyaw.table.admin.enterprise.dao.EStoreDao;
import com.cyyaw.table.admin.enterprise.entity.EStore;
import com.cyyaw.table.store.goods.dao.GGoodsDao;
import com.cyyaw.table.store.goods.dao.GGoodsSearchDao;
import com.cyyaw.table.store.goods.dao.GStoreGoodsSkuDao;
import com.cyyaw.table.store.goods.entity.GGoods;
import com.cyyaw.table.store.goods.entity.GGoodsSearch;
import com.cyyaw.table.store.goods.entity.GStoreGoodsSku;
import com.cyyaw.tx.web.service.GGoodsService;
import com.cyyaw.tx.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GGoodsSearchDao gGoodsSearchDao;

    @Autowired
    private GGoodsDao gGoodsDao;

    @Autowired
    private GGoodsService gGoodsService;

    @Autowired
    private EStoreDao eStoreDao;

    @Autowired
    private GStoreGoodsSkuDao gStoreGoodsSkuDao;

    @Override
    public Page<GGoodsSearch> searchGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size) {
        // 第一步：查找搜索表
        page = page == null ? 1 : page;
        size = size == null ? 30 : size;
        gGoodsSearch = gGoodsSearch == null ? new GGoodsSearch() : gGoodsSearch;
        PageRequest of = PageRequest.of(page - 1, size, Sort.by("istop").descending().and(Sort.by("evaluate").descending()));
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);  //改变默认字符串匹配方式：模糊查询
        matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith());
        matcher.withIgnorePaths("id");  // 忽略属性：id
        matcher.withIgnoreCase(true);  //改变默认大小写忽略方式：忽略大小写
        Example<GGoodsSearch> example = Example.of(gGoodsSearch, matcher);
        return gGoodsSearchDao.findAll(example, of);
    }

    @Override
    public List<GoodsEntity> indexGoods(GGoodsSearch gGoodsSearch, Integer page, Integer size) {
        List<GoodsEntity> goodsEntityList = new ArrayList<>();

        // 查列表
        Page<GGoodsSearch> goods = searchGoods(gGoodsSearch, page, size);
        List<GGoodsSearch> list = goods.getContent();
        List<String> goodsids = new ArrayList<>();
        List<String> storegoodsids = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            GGoodsSearch js = list.get(i);
            goodsids.add(js.getGoodsId());
            storegoodsids.add(js.getStoreGoodsId());
        }
        // 查商品详情
        List<GGoods> goodsInfo = gGoodsDao.findByTidIn(goodsids);
        // 查门店
        List<EStore> storeList = eStoreDao.findByTidIn(storegoodsids);
        // 查库存
        List<GStoreGoodsSku> storeSkus = gStoreGoodsSkuDao.findAllByGoodsid(goodsids);
        // 查活动
        // 查优惠

        // 数据整理
        for (int i = 0; i < list.size(); i++) {
            GoodsEntity goodsEntity = new GoodsEntity();
            GGoodsSearch goodsSearch = list.get(i);
            goodsEntity.setGoodsSearch(goodsSearch);

            String goodsid = goodsSearch.getGoodsId();
            String storegoodsid = goodsSearch.getStoreGoodsId();

            for (int g = 0; g < goodsInfo.size(); g++) {
                GGoods gGoods = goodsInfo.get(g);
                if (goodsid.equals(gGoods.getTid())) {
                    goodsEntity.setGGoods(gGoods);
                    break;
                }
            }

            for (int n = 0; n < storeList.size(); n++) {
                EStore eStore = storeList.get(n);
                if (storegoodsid.equals(eStore.getTid())) {
                    goodsEntity.setEStore(eStore);
                    break;
                }
            }

            for (int m = 0; m < storeSkus.size(); m++) {
                List<GStoreGoodsSku> gStoreGoodsSkuList = new ArrayList<>();
                GStoreGoodsSku sku = storeSkus.get(m);
                if (goodsid.equals(sku.getGoodsid())) {
                    GStoreGoodsSku gStoreGoodsSku = goodsEntity.getGStoreGoodsSku();
                    if (null == gStoreGoodsSku) {
                        goodsEntity.setGStoreGoodsSku(sku);
                    }
                    gStoreGoodsSkuList.add(sku);
                }
                goodsEntity.setGStoreGoodsSkuList(gStoreGoodsSkuList);
            }
            goodsEntityList.add(goodsEntity);
        }
        return goodsEntityList;
    }

    @Override
    public GoodsEntity findGoodsById(Integer id) {
        GoodsEntity goodsEntity = new GoodsEntity();
        GGoods goods = gGoodsService.findId(id);
        goodsEntity.setGGoods(goods);
        return goodsEntity;
    }

    @Override
    public GoodsEntity findGoodsBySkuId(Integer skuid) {
        GoodsEntity goodsEntity = new GoodsEntity();

        // 查Sku
        GStoreGoodsSku goodsSku = gStoreGoodsSkuDao.findByid(skuid);
        // 查商品
        String goodsid = goodsSku.getGoodsid();
        GGoods gGoods = gGoodsService.findByTid(goodsid);
        // 查sku 列表
        List<String> goodsids = new ArrayList<>();
        goodsids.add(gGoods.getTid());
        List<GStoreGoodsSku> gStoreGoodsSkuList = gStoreGoodsSkuDao.findAllByGoodsid(goodsids);
        // 查门店
        String storegoodsid = gGoods.getStoreId();
        EStore eStore = eStoreDao.findByTid(storegoodsid);

        //================ 数据
        goodsEntity.setGStoreGoodsSku(goodsSku);
        goodsEntity.setGGoods(gGoods);
        goodsEntity.setGStoreGoodsSkuList(gStoreGoodsSkuList);
        goodsEntity.setEStore(eStore);
        return goodsEntity;
    }
}
