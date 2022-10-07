package com.cyyaw.table.sql.entity;

import cn.cyyaw.jpa.entity.BaseTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "c_sql")
@org.hibernate.annotations.Table(appliesTo = "c_sql", comment = "查询语句")
public class CSql extends BaseTable implements Serializable {
    private static final long serialVersionUID = 1665182321135876L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", columnDefinition = "int auto_increment not null COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "countsql", columnDefinition = "text COMMENT 'count数量'")
    private String countsql;
    @Basic
    @Column(name = "createtime", columnDefinition = "datetime COMMENT '创建时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @Basic
    @Column(name = "del", columnDefinition = "int COMMENT '是否删除{0:否,1:是}'")
    private Integer del;
    @Basic
    @Column(name = "name", columnDefinition = "varchar(32) not null COMMENT '名称'")
    private String name;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
    @Basic
    @Column(name = "sqlcontent", columnDefinition = "text COMMENT 'sql内容'")
    private String sqlcontent;
    @Basic
    @Column(name = "tid", columnDefinition = "varchar(32) not null COMMENT 'tid'")
    private String tid;
}
