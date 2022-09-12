package com.cyyaw.table.company.entity;

import cn.cyyaw.jpa.entity.BaseTable;
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



}
