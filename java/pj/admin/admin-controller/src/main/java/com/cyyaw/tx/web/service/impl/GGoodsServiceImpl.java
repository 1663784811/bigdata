package com.cyyaw.tx.web.service.impl;

import com.cyyaw.config.exception.WebException;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.table.store.goods.dao.GDetailsDao;
import com.cyyaw.table.store.goods.dao.GGoodsDao;
import com.cyyaw.table.store.goods.dao.GPhotoDao;
import com.cyyaw.table.store.goods.dao.GStoreGoodsSkuDao;
import com.cyyaw.table.store.goods.entity.GDetails;
import com.cyyaw.table.store.goods.entity.GGoods;
import com.cyyaw.table.store.goods.entity.GPhoto;
import com.cyyaw.table.store.goods.entity.GStoreGoodsSku;
import com.cyyaw.tx.web.service.GGoodsService;
import com.cyyaw.util.tools.PageRespone;
import com.cyyaw.util.tools.WhyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GGoodsServiceImpl extends BaseService<GGoods, Integer> implements GGoodsService {


    @Autowired
    private GGoodsDao gGoodsDao;


    @Autowired
    private GPhotoDao gPhotoDao;

    @Autowired
    private GDetailsDao gDetailsDao;

    @Autowired
    private GStoreGoodsSkuDao gStoreGoodsSkuDao;

    @Override
    public BaseDao getBaseDao() {
        return gGoodsDao;
    }

    /**
     * 保存商品
     *
     * @param gGoods
     * @param photoList
     * @param skuList
     * @param gDetails
     * @return
     */
    @Transient
    @Override
    public GGoods saveGoods(GGoods gGoods, List<GPhoto> photoList, List<GStoreGoodsSku> skuList, GDetails gDetails) {
        // === 第一步: 保存商品数据
        if(null == gGoods.getTid()){
            gGoods.setTid(WhyStringUtil.getUUID());
        }
        if(null == gGoods.getCreateTime()){
            gGoods.setCreateTime(new Date());
        }
        if(WhyStringUtil.isEmpty(gGoods.getName())){
            WebException.fail("请输入商品名称");
        }
        GGoods goods = gGoodsDao.save(gGoods);
        String goodsTid = goods.getTid();
        // === 第二步: 保存图片列表
        for(int i=0;i<photoList.size();i++){
            GPhoto gPhoto = photoList.get(i);
            if(null == gPhoto.getCreateTime()){
                gPhoto.setCreateTime(new Date());
            }
            gPhoto.setGoodsId(goodsTid);
            gPhotoDao.save(gPhoto);
        }
        //==== 第三步：保存sku
        for(int i=0;i<skuList.size();i++){
            GStoreGoodsSku sku = skuList.get(i);
            if(null == sku.getCreateTime()){
                sku.setCreateTime(new Date());
            }
            if(null == sku.getTid()){
                sku.setTid(WhyStringUtil.getUUID());
            }
            sku.setGoodsid(goodsTid);
            gStoreGoodsSkuDao.save(sku);
        }
        //==== 第四步：保存商品详情
        if(null == gDetails.getCreateTime()){
            gDetails.setCreateTime(new Date());
        }
        gDetails.setGoodsId(goodsTid);
        gDetailsDao.save(gDetails);
        return goods;
    }

    @Override
    public GGoods findByTid(String tid) {
        return gGoodsDao.findByTid(tid);
    }

    @Override
    public PageRespone<GGoods> findPageGGoodsNoSearch(SelectEntity selectEntity) {
        PageRequest of = PageRequest.of(selectEntity.getPage()-1, selectEntity.getSize());
        List<GGoods> list = gGoodsDao.findPageGGoodsNoSearch(of);
        PageRespone<GGoods> respone = new PageRespone<>();
        respone.setContent(list);
        respone.setPage(selectEntity.getPage()-1);
        respone.setSize(selectEntity.getSize());
        return respone;
    }


}
