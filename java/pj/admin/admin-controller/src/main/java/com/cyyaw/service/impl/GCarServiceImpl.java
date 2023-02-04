//package com.cyyaw.service.impl;
//
//import com.cyyaw.entity.AddMyCar;
//import com.cyyaw.entity.MyCarEntity;
//import com.cyyaw.jpa.BaseDao;
//import com.cyyaw.jpa.BaseService;
//import com.cyyaw.table.store.goods.GCar;
//import com.cyyaw.table.store.goods.GGoods;
//import com.cyyaw.table.store.goods.GStoreGoodsSku;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//@Transactional
//@Slf4j
//public class GCarServiceImpl extends BaseService<GCar, Integer> implements GCarService {
//
//    @Autowired
//    private GCarDao gCarDao;
//
//    @Autowired
//    private GGoodsDao gGoodsDao;
//
//    @Autowired
//    private GStoreGoodsSkuDao gStoreGoodsSkuDao;
//
//    @Override
//    public BaseDao getBaseDao() {
//        return gCarDao;
//    }
//
//
//    @Override
//    public List<MyCarEntity> myCar(String uid, Integer page, Integer size) {
//        List<MyCarEntity> myCarEntityList = new ArrayList<>();
//        page = page == null ? 1 : page;
//        size = size == null ? 30 : size;
//        PageRequest of = PageRequest.of(page - 1, size, Sort.by("createtime").descending());
//        List<GCar> gCars = gCarDao.myCar(uid, of);
//
//
//        List<String> goodsStr = new ArrayList<>();
//        for (GCar gCar:gCars) {
//            goodsStr.add(gCar.getGoodsid());
//        }
//
//        // 查商品
//        List<GGoods> gGoodsList = gGoodsDao.findByTidIn(goodsStr);
//        // 查sku
//        List<GStoreGoodsSku> gStoreGoodsSkuList = gStoreGoodsSkuDao.findAllByGoodsid(goodsStr);
//
//        for (GCar gCar:gCars) {
//            MyCarEntity myCarEntity = new MyCarEntity();
//            myCarEntity.setGCar(gCar);
//            String goodsid = gCar.getGoodsid();
//            String skuid = gCar.getSkuid();
//
//            // 商品
//            for (int i = 0; i <gGoodsList.size(); i++) {
//                GGoods gGoods = gGoodsList.get(i);
//                if(null!= goodsid && goodsid.equals(gGoods.getTid())){
//                    myCarEntity.setGGoods(gGoods);
//                    break;
//                }
//            }
//            List<GStoreGoodsSku> skuList = new ArrayList<>();
//            for (int i = 0; i < gStoreGoodsSkuList.size(); i++) {
//                GStoreGoodsSku goodsSku = gStoreGoodsSkuList.get(i);
//                String goodsidstr = goodsSku.getGoodsid();
//                if(null!= goodsid && goodsid.equals(goodsidstr)){
//                    skuList.add(goodsSku);
//                    if(skuid.equals(goodsSku.getTid())){
//                        myCarEntity.setGStoreGoodsSku(goodsSku);
//                    }
//                }
//            }
//            myCarEntity.setGStoreGoodsSkuList(skuList);
//            myCarEntityList.add(myCarEntity);
//        }
//
//        return myCarEntityList;
//    }
//
//    @Override
//    public MyCarEntity addCar(String uid, String userName, AddMyCar addMyCar) {
//        MyCarEntity myCarEntity = new MyCarEntity();
//
//        String skuid = addMyCar.getSkuid();
//        Integer number = addMyCar.getNumber();
//        List<GCar> gCars = gCarDao.findBySkuIdAndUid(skuid, uid);
//        if(null != gCars && gCars.size()>0){
//            GCar gCar = gCars.get(0);
//            gCar.setNumber(gCar.getNumber()+number);
//            GCar save = gCarDao.save(gCar);
//            myCarEntity.setGCar(save);
//        }else{
//            GStoreGoodsSku goodsSku = gStoreGoodsSkuDao.findByTid(skuid);
//            GCar gCar = new GCar();
//            gCar.setTid(IdWorker.nextId());
//            gCar.setSkuid(skuid);
//            gCar.setNumber(number);
//            gCar.setUserid(uid);
//            gCar.setUsername(userName);
//            gCar.setGoodsid(null!=goodsSku?goodsSku.getGoodsid():null);
//            GCar save = gCarDao.save(gCar);
//            myCarEntity.setGCar(save);
//        }
//        return myCarEntity;
//    }
//
//    @Override
//    public Integer carNumber(String uid) {
//        return gCarDao.countByUid(uid);
//    }
//
//    @Override
//    public void delCarData(String uid, String skuid) {
//        List<GCar> list = gCarDao.findBySkuIdAndUid(skuid, uid);
//        for (int i = 0; i <list.size(); i++) {
//            GCar gCar = list.get(i);
//            gCarDao.delete(gCar);
//        }
//    }
//}
//
