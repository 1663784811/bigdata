package com.cyyaw.service.impl;

import com.cyyaw.store.table.goods.dao.GGoodsDao;
import com.cyyaw.store.table.goods.entity.GGoods;

import java.math.BigDecimal;

import com.cyyaw.enterprise.table.dao.EStoreDao;
import com.cyyaw.enterprise.table.entity.EStore;
import com.cyyaw.util.entity.*;
import com.google.common.collect.Lists;

import com.cyyaw.service.OrderService;
import com.cyyaw.store.table.goods.dao.GStoreGoodsSkuDao;
import com.cyyaw.store.table.goods.entity.GStoreGoodsSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GStoreGoodsSkuDao gStoreGoodsSkuDao;

    @Autowired
    private EStoreDao eStoreDao;

    @Autowired
    private GGoodsDao gGoodsDao;

    @Override
    public CountGoodsRst countGoodsPrice(SubmitOrder submitOrder) {
        List<StoreRest> storeRestList = new ArrayList<>();

        List<CountGoods> goodsList = submitOrder.getGoodsList();
        List<String> skuIdList = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            CountGoods countGoods = goodsList.get(i);
            skuIdList.add(countGoods.getSkuId());
        }

        // 查询sku
        List<GStoreGoodsSku> goodsSkuList = gStoreGoodsSkuDao.findByTidIn(skuIdList);

        List<String> storeIdList = new ArrayList<>();
        List<String> goodsIdList = new ArrayList<>();
        for (GStoreGoodsSku sku : goodsSkuList) {
            String storeId = sku.getStoreId();
            String gId = sku.getGoodsId();
            storeIdList.add(storeId);
            goodsIdList.add(gId);
        }
        // 门店
        List<EStore> storeList = eStoreDao.findByTidIn(storeIdList);
        // 查商品
        List<GGoods> gGoodsList = gGoodsDao.findByTidIn(goodsIdList);

        // 拆分门店
        for (int i = 0; i < goodsList.size(); i++) {
            CountGoods countGoods = goodsList.get(i);
            String skuId = countGoods.getSkuId();
            Integer number = countGoods.getNumber();
            // 查找
            for (int j = 0; j < goodsSkuList.size(); j++) {
                GStoreGoodsSku gStoreGoodsSku = goodsSkuList.get(j);
                String storeId = gStoreGoodsSku.getStoreId();
                String skuTid = gStoreGoodsSku.getTid();
                String goodsId = gStoreGoodsSku.getGoodsId();
                if (skuTid.equals(skuId)) {
                    EStore restStore = null;
                    for (int k = 0; k < storeList.size(); k++) {
                        EStore store = storeList.get(k);
                        String storeTid = store.getTid();
                        if (storeTid.equals(storeId)) {
                            restStore = store;
                            break;
                        }
                    }
                    //==
                    if (restStore != null) {
                        String storeTid = restStore.getTid();
                        boolean h = false;
                        for (int k = 0; k < storeRestList.size(); k++) {
                            StoreRest storeRest = storeRestList.get(k);
                            String sId = storeRest.getStoreId();
                            if (sId.equals(storeTid)) {
                                // 找到了
                                List<GoodsRest> goodsRestList = storeRest.getGoodsRstList();
                                // set商品
                                GoodsRest goodsRest = new GoodsRest();
                                for (int m = 0; m < gGoodsList.size(); m++) {
                                    GGoods gGoods = gGoodsList.get(m);
                                    String tid = gGoods.getTid();
                                    if (tid.equals(goodsId)) {
                                        goodsRest.setGGoods(gGoods);
                                        break;
                                    }
                                }
                                goodsRest.setGStoreGoodsSku(gStoreGoodsSku);
                                goodsRest.setNumber(number);
                                BigDecimal price = gStoreGoodsSku.getPrice();
                                goodsRest.setPrice(price);
                                goodsRest.setTotalPrice(price.multiply(new BigDecimal(number)));
                                goodsRestList.add(goodsRest);
                                // ===  更新价格
                                BigDecimal allPrice = storeRest.getAllTotalPrice();
                                storeRest.setAllTotalPrice(allPrice.add(goodsRest.getTotalPrice()));
                                BigDecimal goodsPrice = storeRest.getGoodsTotalPrice();
                                storeRest.setGoodsTotalPrice(goodsPrice.add(goodsRest.getTotalPrice()));
                                BigDecimal goodsN = storeRest.getGoodsNum();
                                storeRest.setGoodsNum(goodsN.add(new BigDecimal(goodsRest.getNumber())));
                                BigDecimal exp = storeRest.getExpressPrice();
                                storeRest.setExpressPrice(exp.add(new BigDecimal("0")));
                                h = true;
                                break;
                            }
                        }

                        // 没有找到
                        if (!h) {
                            //
                            List<GoodsRest> goodsRestList = new ArrayList<>();
                            GoodsRest goodsRest = new GoodsRest();
                            for (int k = 0; k < gGoodsList.size(); k++) {
                                GGoods gGoods = gGoodsList.get(k);
                                String tid = gGoods.getTid();
                                if (tid.equals(goodsId)) {
                                    goodsRest.setGGoods(gGoods);
                                    break;
                                }
                            }
                            goodsRest.setGStoreGoodsSku(gStoreGoodsSku);
                            goodsRest.setNumber(number);
                            BigDecimal price = gStoreGoodsSku.getPrice();
                            goodsRest.setPrice(price);
                            goodsRest.setTotalPrice(price.multiply(new BigDecimal(number)));
                            goodsRestList.add(goodsRest);
                            // ===
                            StoreRest storeRest = new StoreRest();
                            storeRest.setGoodsRstList(goodsRestList);
                            storeRest.setAllTotalPrice(goodsRest.getTotalPrice());
                            storeRest.setGoodsTotalPrice(goodsRest.getTotalPrice());
                            storeRest.setGoodsNum(new BigDecimal(goodsRest.getNumber()));
                            storeRest.setExpressPrice(new BigDecimal("0"));
                            storeRest.setStoreId(storeTid);
                            storeRest.setEStore(restStore);
                            storeRestList.add(storeRest);
                        }
                    }
                }
            }

        }
        // =================================   整理数据
        BigDecimal allTotalPrice = new BigDecimal("0");
        BigDecimal goodsTotalPrice = new BigDecimal("0");
        BigDecimal goodsNum = new BigDecimal("0");
        BigDecimal expressPrice = new BigDecimal("0");
        for (int i = 0; i < storeRestList.size(); i++) {
            StoreRest storeRest = storeRestList.get(i);
            allTotalPrice = allTotalPrice.add(storeRest.getAllTotalPrice());
            goodsTotalPrice = goodsTotalPrice.add(storeRest.getGoodsTotalPrice());
            goodsNum = goodsNum.add(storeRest.getGoodsNum());
            expressPrice = expressPrice.add(storeRest.getExpressPrice());
        }
        CountGoodsRst countGoodsRst = new CountGoodsRst();
        countGoodsRst.setAllTotalPrice(allTotalPrice);
        countGoodsRst.setGoodsTotalPrice(goodsTotalPrice);
        countGoodsRst.setGoodsNum(goodsNum);
        countGoodsRst.setExpressPrice(expressPrice);
        countGoodsRst.setStoreRestList(storeRestList);
        return countGoodsRst;
    }

    @Override
    public void createOrder(SubmitOrder submitOrder) {
        // 判断基本信息是否正确

        // 计算价格


        // 生成订单


    }


}
