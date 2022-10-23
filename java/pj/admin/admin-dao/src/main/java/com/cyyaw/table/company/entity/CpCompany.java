package com.cyyaw.table.company.entity;

import com.cyyaw.jpa.entity.BaseTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cp_company")
@org.hibernate.annotations.Table(appliesTo = "cp_company", comment = "公司")
public class CpCompany extends BaseTable implements Serializable {

    private static final long serialVersionUID = 1573661935283L;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '公司名称'")
    private String name;

    @Basic
    @Column(name = "establish_time", columnDefinition = "datetime COMMENT '成立时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date establishTime;


    @Basic
    @Column(name = "legal_person", columnDefinition = "varchar(255) COMMENT '法人'")
    private String legalPerson;

    @Basic
    @Column(name = "stock_type", columnDefinition = "int COMMENT '地区类型{1:A股,2:港股,3:美股}'")
    private Integer stockType;

    @Basic
    @Column(name = "stock_name", columnDefinition = "varchar(255) COMMENT '股票名称'")
    private String stockName;

    @Basic
    @Column(name = "stock_no", columnDefinition = "varchar(32) COMMENT '证券代码'")
    private String stockNo;
}
