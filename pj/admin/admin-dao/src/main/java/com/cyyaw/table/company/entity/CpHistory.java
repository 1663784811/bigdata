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
@Table(name = "cp_history")
@org.hibernate.annotations.Table(appliesTo = "cp_history", comment = "公司历史")
public class CpHistory extends BaseTable implements Serializable {

    private static final long serialVersionUID = 157366193283L;

    @Basic
    @Column(name = "cp_id", columnDefinition = "varchar(32) COMMENT '公司ID'")
    private String cpId;

    @Basic
    @Column(name = "history_time", columnDefinition = "datetime COMMENT '时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date historyTime;

    @Basic
    @Column(name = "cp_describe", columnDefinition = "text COMMENT '描述'")
    private String cpDescribe;

    @Basic
    @Column(name = "analysis", columnDefinition = "text COMMENT '分析'")
    private String analysis;


}
