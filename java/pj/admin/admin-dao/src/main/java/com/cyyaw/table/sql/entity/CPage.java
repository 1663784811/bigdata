package com.cyyaw.table.sql.entity;

import com.cyyaw.jpa.entity.BaseTable;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "c_page")
@org.hibernate.annotations.Table(appliesTo = "c_page", comment = "页面")
public class CPage extends BaseTable implements Serializable {

    private static final long serialVersionUID = 166582321135876L;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(32) not null COMMENT '名称'")
    private String name;

    @Basic
    @Column(name = "page_icon", columnDefinition = "text COMMENT '图标'")
    private String pageIcon;


}
