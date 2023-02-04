//package com.cyyaw.service.impl;
//
//import com.cyyaw.entity.*;
//import com.cyyaw.jpa.BaseDao;
//import com.cyyaw.jpa.BaseService;
//import com.cyyaw.table.store.goods.GGoods;
//import com.cyyaw.table.store.goods.GStoreGoodsSku;
//import com.cyyaw.table.store.order.ODetails;
//import com.cyyaw.table.store.order.OOrder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
//@Service
//@Transactional
//@Slf4j
//public class OOrderServiceImpl extends BaseService<OOrder, Integer> implements OOrderService {
//
//    @Autowired
//    private OOrderDao oOrderDao;
//
//    @Autowired
//    private ODetailsDao oDetailsDao;
//
//    @Autowired
//    private GStoreGoodsSkuService gStoreGoodsSkuService;
//
//
//    @Override
//    public BaseDao getBaseDao() {
//        return oOrderDao;
//    }
//
//
//    /**
//     * 获取我的订单
//     *
//     * @param uid    用户ID
//     * @param page   分页
//     * @param size   大小
//     * @param status 状态
//     * @return
//     */
//    @Override
//    public List<OrderEntity> myOrder(String uid, Integer page, Integer size, Integer status) {
//        List<OrderEntity> orderEntityList = new ArrayList<>();
//        // 第一步：
//        page = page == null ? 1 : page;
//        size = size == null ? 30 : size;
//        PageRequest of = PageRequest.of(page - 1, size, Sort.by("createtime").descending());
//
//        // 第二步：
//        OOrder query = new OOrder();
//        query.setUserid(uid);
//        Example<OOrder> example = Example.of(query);
//
//        // 第三步：
//        Page<OOrder> oOrderPage = oOrderDao.findAll(example, of);
//        List<OOrder> content = oOrderPage.getContent();
//
//        // 第四步： 查详情
//        List<String> orderIdArr = new ArrayList<>();
//        for (OOrder order: content) {
//            orderIdArr.add(order.getTid());
//        }
//        List<ODetails> detailsList = oDetailsDao.findByOrderIdArr(orderIdArr);
//
//        for (OOrder order : content) {
//            String tid = order.getTid();
//            OrderEntity orderEntity = new OrderEntity();
//            orderEntity.setOOrder(order);
//            List<ODetails> details = new ArrayList<>();
//            for (int i = 0; i <detailsList.size(); i++) {
//                ODetails oDetails = detailsList.get(i);
//                if(tid.equals(oDetails.getOrderid())){
//                    details.add(oDetails);
//                }
//            }
//            orderEntity.setODetailsList(details);
//            orderEntityList.add(orderEntity);
//        }
//        return orderEntityList;
//    }
//
//    @Override
//    public OrderEntity myOrderDetails(Integer id) {
//        OrderEntity orderEntity = new OrderEntity();
//        OOrder order = oOrderDao.findByid(id);
//        orderEntity.setOOrder(order);
//        List<String> orderIdArr = new ArrayList<>();
//        orderIdArr.add(order.getTid());
//        List<ODetails> detailsList = oDetailsDao.findByOrderIdArr(orderIdArr);
//        orderEntity.setODetailsList(detailsList);
//        return orderEntity;
//    }
//
//
//
//    @Override
//    public SubmitOrderRest submitOrder(SubmitOrder submitOrder) {
//        SubmitOrderRest rest = new SubmitOrderRest();
//
//        OOrder order = new OOrder();
//        order.setTid(IdWorker.nextId());
//        order.setCreatetime(new Date());
//        order.setOrderno(IdWorker.nextId());
//        order.setStoreid("");
//        order.setStorename("听心小店");
//        // 计算商品价格
//        List<ComputGoods> goodsList = submitOrder.getGoodsList();
//        ComputGoodsRst computGoodsRst = gStoreGoodsSkuService.computGoods(goodsList);
//        //
//        computGoodsRst.getGoodsRstList();
//
//        order.setUserid(submitOrder.getUid());
//
//        order.setAddressid(submitOrder.getAddressId());
//        order.setAddressdetail(submitOrder.getAddress());
//        order.setPhone(submitOrder.getPhone());
//        order.setUsername(submitOrder.getUserName());
//        order.setType(0);
//        order.setAmount(computGoodsRst.getGoodsTotalPrice());
//        order.setNumber(computGoodsRst.getGoodsNum().intValue());
//        order.setExpressprice(computGoodsRst.getExpressprice());
//        order.setDescription(submitOrder.getDescription());
//        order.setPayableamount(computGoodsRst.getAllTotalPrice());
//
//        // ==================   生成订单详情
//
//        List<GoodsRst> goodsRstList = computGoodsRst.getGoodsRstList();
//
//        List<ODetails> oDetailsList = new ArrayList<>();
//
//        for (GoodsRst goodsRst: goodsRstList) {
//
//            ODetails oDetails = new ODetails();
//            oDetails.setTid(IdWorker.nextId());
//            oDetails.setCreatetime(new Date());
//
//            oDetails.setDel(0);
//            oDetails.setOrderid(order.getTid());
//            GStoreGoodsSku goodsSku = goodsRst.getGStoreGoodsSku();
//
//            GGoods gGoods = goodsRst.getGGoods();
//            oDetails.setGoodsid(goodsSku.getGoodsid());
//            oDetails.setName(gGoods.getName());
//
//            oDetails.setPhoto(gGoods.getPhoto());
//            oDetails.setSkuid(goodsSku.getTid());
//            oDetails.setType(0);
//
//            oDetails.setNumber(goodsRst.getNumber());
//            oDetails.setPrice(goodsRst.getPrice());
//            // ===========
//            oDetailsList.add(oDetails);
//        }
//
//        // 生成订单
//        oOrderDao.save(order);
//        // 生成订单详情
//        for (ODetails oDetails: oDetailsList) {
//            oDetails = oDetailsDao.save(oDetails);
//        }
//
//        rest.setOrder(order);
//        rest.setODetailsList(oDetailsList);
//        return rest;
//    }
//
//    @Override
//    public OOrder findByTid(String tid) {
//      return   oOrderDao.findByTid(tid);
//    }
//}
//
