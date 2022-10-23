package com;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询参数
 */
@Data
public class ${__Table__}Query implements Serializable, BaseQuery{
    private static final long serialVersionUID = ${operationTools.getserialVersionUID()}L;

    private Integer page =1;

    private Integer size =30;
    /**
     * DESC  ASC
     */
    private  String sort;
    /**
     * 字段
     */
    private String orderBy;

<#-- ============================     字段列表     ======================== -->
<#list javaColumns as column>
    @ApiModelProperty("${column.note}")
    private ${column.javaType} ${operationTools.indexToLowerCase(column.columnName)};
</#list>
}
