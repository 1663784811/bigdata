package com;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ${javaData.tableNote}
 */

@Data
@Table(name = "${javaData.table}")
public class ${__Table__} implements Serializable{
    private static final long serialVersionUID = ${operationTools.getserialVersionUID()}L;
<#-- ============================     字段列表     ======================== -->

${tkJavaEntity.beanList}

}
