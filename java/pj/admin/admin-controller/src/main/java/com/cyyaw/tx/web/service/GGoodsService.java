//package com.cyyaw.tx.web.service;
//
//import com.cyyaw.jpa.BaseTableService;
//import com.cyyaw.jpa.util.entity.SelectEntity;
//import com.cyyaw.table.store.goods.entity.GDetails;
//import com.cyyaw.table.store.goods.entity.GGoods;
//import com.cyyaw.table.store.goods.entity.GPhoto;
//import com.cyyaw.table.store.goods.entity.GStoreGoodsSku;
//import com.cyyaw.util.tools.PageRespone;
//
//import java.util.List;
//
//public interface GGoodsService extends BaseTableService<GGoods, Integer> {
//    /**
//     * 保存商品
//     */
//    GGoods saveGoods(GGoods gGoods, List<GPhoto> photoList, List<GStoreGoodsSku> skuList, GDetails gDetails);
//
//    GGoods findByTid(String tid);
//
//    PageRespone<GGoods> findPageGGoodsNoSearch(SelectEntity selectEntity);
//}
