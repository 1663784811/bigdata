package com.cyyaw.service.impl;

import com.cyyaw.enterprise.table.dao.EStoreDao;
import com.cyyaw.enterprise.table.entity.EStore;
import com.cyyaw.service.CartService;
import com.cyyaw.store.table.goods.dao.GCartDao;
import com.cyyaw.store.table.goods.dao.GStoreGoodsSkuDao;
import com.cyyaw.store.table.goods.entity.GCart;
import com.cyyaw.store.table.goods.entity.GStoreGoodsSku;
import com.cyyaw.util.entity.CartListResponse;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private GCartDao gCartDao;
    @Autowired
    private EStoreDao eStoreDao;

    @Autowired
    private GStoreGoodsSkuDao gStoreGoodsSkuDao;

    @Override
    public BaseResult myCartList() {

        String userId = "";

        //  第一步: 根据门店分组，查出有多少门店
        gCartDao.findAll();

        GCart gCart = new GCart();
        ExampleMatcher matcher = ExampleMatcher.matching();
        PageRequest of = PageRequest.of(0, 10);


        Example<GCart> ex = Example.of(gCart, matcher);
        Page<GCart> gCartPage = gCartDao.findAll(ex, of);

        BaseResult.Result result = new BaseResult.Result();
        result.setPage(gCartPage.getTotalPages());
        result.setSize(gCartPage.getSize());
        result.setTotal(gCartPage.getTotalElements());


        List<GCart> gCartList = gCartPage.getContent();
        List<String> storeIdList = new ArrayList<>();
        for (GCart cart : gCartList) {
            storeIdList.add(cart.getStoreId());
        }

        // 第二步:获取门店
        List<EStore> storeList = eStoreDao.findByTidIn(storeIdList);
        // 第三步: 查询购物车里的商品(sku)
        List<GCart> gCartListObj = gCartDao.findByUidAndStoreIdIn(userId, storeIdList);
        // 第四步: 查商品SKU
        List<String> skuIdList = new ArrayList<>();
        for (int i = 0; i < gCartListObj.size(); i++) {
            skuIdList.add(gCartListObj.get(i).getSkuId());
        }
        List<GStoreGoodsSku> goodsSkuList = gStoreGoodsSkuDao.findByTidIn(skuIdList);

        // ================ 整理数据
        List<CartListResponse> data = new ArrayList<>();
        for (int i = 0; i < storeList.size(); i++) {
            EStore storeObj = storeList.get(i);
            String storeId = storeObj.getTid();
            CartListResponse rest = new CartListResponse();
            rest.setEStore(storeObj);
            // ========== 整理购物车
            List<GCart> cartList = new ArrayList();
            for (int j = 0; j < gCartListObj.size(); j++) {
                GCart cart = gCartListObj.get(j);
                if (storeId.equals(cart.getStoreId())) {
                    // ====== 整理sku
                    for (int k = 0; k < goodsSkuList.size(); k++) {
                        GStoreGoodsSku gStoreGoodsSku = goodsSkuList.get(k);
                        if (gStoreGoodsSku.getTid().equals(cart.getSkuId())) {
                            cart.setGoodsSku(gStoreGoodsSku);
                        }
                    }
                    cartList.add(cart);
                }
            }
            rest.setCartList(cartList);
            data.add(rest);
        }


        return BaseResult.ok(data, result);
    }


}
