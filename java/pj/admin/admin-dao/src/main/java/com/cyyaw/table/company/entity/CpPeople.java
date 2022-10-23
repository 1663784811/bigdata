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
@Table(name = "cp_people")
@org.hibernate.annotations.Table(appliesTo = "cp_people", comment = "公司人物关系")
public class CpPeople extends BaseTable implements Serializable {

    private static final long serialVersionUID = 1573661935283L;

    @Basic
    @Column(name = "cp_id", columnDefinition = "varchar(32) COMMENT '公司ID'")
    private String cpId;

    @Basic
    @Column(name = "pp_id", columnDefinition = "varchar(32) COMMENT '人ID'")
    private String ppId;

    @Basic
    @Column(name = "post", columnDefinition = "varchar(255) COMMENT '岗位'")
    private String post;

}
