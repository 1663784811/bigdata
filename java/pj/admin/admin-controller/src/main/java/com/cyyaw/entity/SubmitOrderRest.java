package com.cyyaw.entity;


import com.cyyaw.table.order.ODetails;
import com.cyyaw.table.order.OOrder;
import lombok.Data;

import java.util.List;

@Data
public class SubmitOrderRest {

    /**
     * 订单
     */
    private OOrder order;

    /**
     * 订单详情
     */
    private List<ODetails> oDetailsList;
}
