package com.cyyaw.admin.table.entity;


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
@Table(name = "pp_history")
@org.hibernate.annotations.Table(appliesTo = "pp_history", comment = "人历史")
public class PpHistory extends BaseTable implements Serializable {

    private static final long serialVersionUID = 15736693283L;

    @Basic
    @Column(name = "pp_id", columnDefinition = "varchar(32) COMMENT '人ID'")
    private String ppId;

    @Basic
    @Column(name = "history_time", columnDefinition = "datetime COMMENT '时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date historyTime;

    @Basic
    @Column(name = "describe", columnDefinition = "text default '' COMMENT '描述'")
    private String describe;

    @Basic
    @Column(name = "analysis", columnDefinition = "text default '' COMMENT '分析'")
    private String analysis;


}
