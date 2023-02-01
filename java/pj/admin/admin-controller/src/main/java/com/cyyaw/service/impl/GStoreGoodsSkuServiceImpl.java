package com.cyyaw.service.impl;

import com.cyyaw.config.common.entity.ComputGoods;
import com.cyyaw.config.common.entity.ComputGoodsRst;
import com.cyyaw.config.common.entity.GoodsRst;
import com.cyyaw.config.common.service.BaseDao;
import com.cyyaw.config.common.service.BaseService;
import com.cyyaw.config.common.util.IdWorker;
import com.cyyaw.config.table.service.GStoreGoodsSkuService;
import com.cyyaw.config.table.table.dao.goods.GGoodsDao;
import com.cyyaw.config.table.table.dao.goods.GStoreGoodsSkuDao;
import com.cyyaw.config.table.table.entity.goods.GGoods;
import com.cyyaw.config.table.table.entity.goods.GStoreGoodsSku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
@Slf4j
public class GStoreGoodsSkuServiceImpl extends BaseService<GStoreGoodsSku, Integer> implements GStoreGoodsSkuService {

    @Autowired
    private GStoreGoodsSkuDao gStoreGoodsSkuDao;

    @Autowired
    private GGoodsDao gGoodsDao;

    @Override
    public BaseDao getBaseDao() {
        return gStoreGoodsSkuDao;
    }

    /**
     * 保存列表
     */
    @Override
    public List<GStoreGoodsSku> saveGStoreGoodsSkuList(List<GStoreGoodsSku> gStoreGoodsSkuList) {
        for (GStoreGoodsSku gStoreGoodsSku : gStoreGoodsSkuList) {
            Integer id = gStoreGoodsSku.getId();
            if(ObjectUtils.isEmpty(id)){
                gStoreGoodsSku.setTid(IdWorker.nextId());
                gStoreGoodsSku.setCreatetime(new Date());
                gStoreGoodsSku= gStoreGoodsSkuDao.save(gStoreGoodsSku);
            }else{
                GStoreGoodsSku obj = gStoreGoodsSkuDao.findByid(id);
                BeanUtils.copyProperties(gStoreGoodsSku,obj);
                gStoreGoodsSku= gStoreGoodsSkuDao.save(obj);
            }
        }
        return gStoreGoodsSkuList;
    }

    /**
     * 计算商品价格
     * @param list
     */
    @Override
    public ComputGoodsRst computGoods(List<ComputGoods> list) {
        ComputGoodsRst computGoodsRst = new ComputGoodsRst();
        List<GoodsRst> goodsRstList = new ArrayList<>();
        BigDecimal allTotalPrice = BigDecimal.ZERO;
        BigDecimal goodsTotalPrice = BigDecimal.ZERO;
        BigDecimal goodsNum = BigDecimal.ZERO;

        List<String> idArr = new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            ComputGoods computGoods = list.get(i);
            idArr.add(computGoods.getSkuid());
        }
        List<GStoreGoodsSku> gStoreGoodsSkuList = gStoreGoodsSkuDao.findByTidIn(idArr);
        for (GStoreGoodsSku goodsSku: gStoreGoodsSkuList) {
            GoodsRst goodsRst = new GoodsRst();
            //查找数量
            int num =0;
            String tid = goodsSku.getTid();
            String goodsid = goodsSku.getGoodsid();
            for (int i = 0; i <list.size(); i++) {
                ComputGoods computGoods = list.get(i);
                String skuid = computGoods.getSkuid();
                Integer number = computGoods.getNumber();
                if(tid.equals(skuid) ){
                    num = number;
                }
            }
            // 数量* 价格 = 总价
            BigDecimal price = goodsSku.getPrice();
            goodsNum = goodsNum.add(new BigDecimal(num));

            BigDecimal totalGoods = price.multiply(new BigDecimal(num));
            goodsTotalPrice = goodsTotalPrice.add(totalGoods);
            allTotalPrice = allTotalPrice.add(totalGoods);

            // =====
            goodsRst.setNumber(num);
            goodsRst.setPrice(price);
            goodsRst.setTotalPrice(totalGoods);
            goodsRst.setGStoreGoodsSku(goodsSku);

            GGoods gGoods = gGoodsDao.findByTid(goodsid);
            goodsRst.setGGoods(gGoods);

            goodsRstList.add(goodsRst);
        }
        computGoodsRst.setGoodsNum(goodsNum);
        computGoodsRst.setGoodsTotalPrice(goodsTotalPrice);
        computGoodsRst.setAllTotalPrice(allTotalPrice);
        computGoodsRst.setExpressprice(BigDecimal.ZERO);
        computGoodsRst.setGoodsRstList(goodsRstList);
        return computGoodsRst;
    }
}

