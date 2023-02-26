package com.cyyaw.table.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_admin")
@org.hibernate.annotations.Table(appliesTo = "t_admin", comment = "管理员表")
public class TAdmin implements Serializable {
    private static final long serialVersionUID = 1587301173682985L;

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
    @Column(name = "enterprise_id", columnDefinition = "varchar(32) COMMENT '所属企业e_enterprise表ID'")
    private String enterpriseId;


    // =================================================================================


    @Basic
    @Column(name = "account", columnDefinition = "varchar(32) COMMENT '账号'")
    private String account;
    @Basic
    @Column(name = "canlogintime", length = 19, nullable = true, columnDefinition = "datetime COMMENT '可登录时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date canlogintime;
    @Basic
    @Column(name = "email", nullable = true, columnDefinition = "varchar(255) COMMENT '邮箱'")
    private String email;
    @Basic
    @Column(name = "ip", length = 60, nullable = true, columnDefinition = "varchar(60) COMMENT '最后登录IP'")
    private String ip;
    @Basic
    @Column(name = "lastlogintime", length = 19, nullable = true, columnDefinition = "datetime COMMENT '最后登录时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogintime;
    @Basic
    @Column(name = "nickname", nullable = true, columnDefinition = "varchar(32) COMMENT '昵称'")
    private String nickname;
    @Basic
    @Column(name = "password", length = 64, nullable = true, columnDefinition = "varchar(32) COMMENT '密码'")
    private String password;
    @Basic
    @Column(name = "phone", length = 15, nullable = true, columnDefinition = "varchar(15) COMMENT '手机号'")
    private String phone;
    @Basic
    @Column(name = "salt", nullable = true, columnDefinition = "varchar(32) COMMENT '加密盐'")
    private String salt;
    @Basic
    @Column(name = "status",nullable = true, columnDefinition = "int COMMENT '状态{0:正常,1:暂时锁定,2:永久锁定}'")
    private Integer status;
    @Basic
    @Column(name = "truename", nullable = true, columnDefinition = "varchar(32) COMMENT '真实姓名'")
    private String truename;
}
