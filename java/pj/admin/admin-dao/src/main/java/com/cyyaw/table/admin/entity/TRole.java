package com.cyyaw.table.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_role")
@org.hibernate.annotations.Table(appliesTo = "t_role", comment = "角色表")
public class TRole implements Serializable {
    private static final long serialVersionUID = 1568782627448808L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", columnDefinition = "int auto_increment not null COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "tid", columnDefinition = "varchar(32) unique not null default '' COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "create_time", columnDefinition = "datetime default now() COMMENT '创建时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Basic
    @Column(name = "del", columnDefinition = "int default '0' COMMENT '是否删除{0:否,1:是}'")
    private Integer del;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) default '' COMMENT '备注'")
    private String note;

    // =================================================================================


    @Basic
    @Column(name = "code", length = 32, columnDefinition = "varchar(32) COMMENT '授权码'")
    private String code;
    @Basic
    @Column(name = "name", length = 32, columnDefinition = "varchar(32) COMMENT '角色名称'")
    private String name;


    @Basic
    @Column(name = "pid", length = 10, columnDefinition = "int COMMENT '父级ID'")
    private String pid;

    @Basic
    @Column(name = "tree_code", length = 10, columnDefinition = "varchar(32) not null default '' COMMENT '树码(一级三位)'")
    private String treeCode;

}