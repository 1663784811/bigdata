package com.cyyaw.admin.table.entity;

import cn.cyyaw.jpa.entity.BaseTable;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cp_company")
@org.hibernate.annotations.Table(appliesTo = "cp_company", comment = "公司")
public class CpCompany extends BaseTable {
    private static final long serialVersionUID = 1573661935283L;

    @Basic
    @Column(name = "[name]", columnDefinition = "varchar(255) COMMENT '公司名称'")
    private String name;



}
