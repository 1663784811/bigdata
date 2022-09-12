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
@Table(name = "pp_people")
@org.hibernate.annotations.Table(appliesTo = "pp_people", comment = "人")
public class PpPeople  extends BaseTable implements Serializable {

    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '姓名'")
    private String name;

    @Basic
    @Column(name = "birthday", columnDefinition = "datetime COMMENT '生日'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;



}
