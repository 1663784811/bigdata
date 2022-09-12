package com.cyyaw.table.company.entity;

import cn.cyyaw.jpa.entity.BaseTable;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cp_stock")
@org.hibernate.annotations.Table(appliesTo = "cp_stock", comment = "股票")
public class CpStock extends BaseTable implements Serializable {


    @Basic
    @Column(name = "cp_id", columnDefinition = "varchar(32) COMMENT '公司ID'")
    private String cpId;

    @Basic
    @Column(name = "stock_type", columnDefinition = "int COMMENT '地区类型{1:A股,2:港股,3:美股}'")
    private Integer stockType;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '股票名称'")
    private String name;


    @Basic
    @Column(name = "stock_no", columnDefinition = "varchar(32) COMMENT '证券代码'")
    private String stockNo;

}
