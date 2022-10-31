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
@Table(name = "c_page_components")
@org.hibernate.annotations.Table(appliesTo = "c_page_components", comment = "页面组件")
public class CPageComponents extends BaseTable implements Serializable {

    private static final long serialVersionUID = 1667229296952725L;
    @Basic
    @Column(name = "page_id", columnDefinition = "varchar(45) COMMENT '页面ID'")
    private String pageId;
    @Basic
    @Column(name = "name", columnDefinition = "varchar(45) COMMENT '名称'")
    private String name;
    @Basic
    @Column(name = "components_code", columnDefinition = "varchar(45) COMMENT '类型'")
    private String components_code;
    @Basic
    @Column(name = "data", columnDefinition = "text COMMENT '数据'")
    private String data;
    @Basic
    @Column(name = "icon", columnDefinition = "varchar(255) COMMENT 'icon图标'")
    private String icon;
    @Basic
    @Column(name = "sort", columnDefinition = "int COMMENT '排序'")
    private Integer sort;
}
