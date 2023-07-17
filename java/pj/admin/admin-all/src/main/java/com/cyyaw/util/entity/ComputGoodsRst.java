package com.cyyaw.util.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计算结果返回
 */
@Data
public class ComputGoodsRst {

    /**
     * 总价
     */
    BigDecimal allTotalPrice;

    /**
     * 商品总价
     */
    BigDecimal goodsTotalPrice;

    /**
     * 商品数量
     */
    BigDecimal goodsNum;

    /**
     * 快递
     */
    BigDecimal expressprice;


    /**
     * 商品信息
     */
    List<GoodsRst> goodsRstList;

}
