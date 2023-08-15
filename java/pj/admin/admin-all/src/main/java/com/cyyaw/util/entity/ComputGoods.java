package com.cyyaw.util.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ComputGoods {

    /**
     * sku ID
     */
    @ApiModelProperty(value = "skuID", example = "skuID")
    private String skuId;

    @ApiModelProperty(value = "数量", example = "数量")
    private Integer number;

}
