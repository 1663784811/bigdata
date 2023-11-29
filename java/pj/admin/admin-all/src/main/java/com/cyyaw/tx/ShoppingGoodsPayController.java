package com.cyyaw.tx;


import com.cyyaw.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "支付")
@RestController
@RequestMapping("/shopping/${appId}/pay")
public class ShoppingGoodsPayController {


    @Autowired
    private PayService payOrder;

    @ApiOperation(value = "支付回调", notes = "支付回调")
    @PostMapping("/payBack")
    public void payBack(){



    }

    @ApiOperation(value = "支付订单", notes = "支付订单")
    @PostMapping("/payOrder")
    public void payOrder(String orderId) {
        payOrder.payOrder(orderId);
    }


}
