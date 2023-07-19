package com.cyyaw.util.entity;

import com.cyyaw.table.store.order.entity.ODetails;
import com.cyyaw.table.store.order.entity.OOrder;
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
