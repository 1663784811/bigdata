package com.cyyaw.entity;

import com.cyyaw.table.order.ODetails;
import com.cyyaw.table.order.OOrder;
import lombok.Data;

import java.util.List;


/**
 * 订单
 */
@Data
public class OrderEntity {

    private OOrder oOrder;

    private List<ODetails> oDetailsList;
}
