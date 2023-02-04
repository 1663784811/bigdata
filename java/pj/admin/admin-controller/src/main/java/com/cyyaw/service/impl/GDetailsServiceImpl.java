//package com.cyyaw.service.impl;
//
//import com.cyyaw.jpa.BaseDao;
//import com.cyyaw.jpa.BaseService;
//import com.cyyaw.table.store.goods.GDetails;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//
//@Service
//@Transactional
//@Slf4j
//public class GDetailsServiceImpl extends BaseService<GDetails, Integer> implements GDetailsService {
//
//    @Autowired
//    private GDetailsDao gDetailsDao;
//
//    @Override
//    public BaseDao getBaseDao() {
//        return gDetailsDao;
//    }
//
//    @Override
//    public GDetails findBySkuId(String goodsId) {
//        List<GDetails> list = gDetailsDao.findByGoodsid(goodsId);
//        if(null != list && list.size()>0){
//            return list.get(0);
//        }else{
//            return null;
//        }
//    }
//}
//
