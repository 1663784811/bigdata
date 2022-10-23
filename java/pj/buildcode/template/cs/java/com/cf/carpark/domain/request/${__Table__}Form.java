package com;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;

/**
 * 表单提交（添加、修改）
 * 数据返回
 */
@Data
public class ${__Table__}Form implements Serializable{
    private static final long serialVersionUID = ${operationTools.getserialVersionUID()}L;
<#-- ============================     字段列表     ======================== -->
<#list javaColumns as column>

    @ApiModelProperty("${column.note}")
    private ${column.javaType} ${operationTools.indexToLowerCase(column.columnName)};
</#list>
}
