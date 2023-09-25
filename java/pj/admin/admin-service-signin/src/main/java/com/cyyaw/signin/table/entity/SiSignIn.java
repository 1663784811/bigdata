package com.cyyaw.signin.table.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "si_sign_in")
@org.hibernate.annotations.Table(appliesTo = "si_sign_in", comment = "签到表")
public class SiSignIn implements Serializable {

    private static final long serialVersionUID = 166727146408891L;

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

    // ==================================================

    @Basic
    @Column(name = "appId", columnDefinition = "varchar(255) COMMENT '应用ID'")
    private String appId;

    // ==================================================

    @Basic
    @Column(name = "title", columnDefinition = "varchar(255) COMMENT '标题'")
    private String title;

    @Basic
    @Column(name = "status", columnDefinition = "int COMMENT '状态{0:创建,1:完成}'")
    private Integer status;

    @Basic
    @Column(name = "start_time", columnDefinition = "datetime COMMENT '开始时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @Basic
    @Column(name = "end_time", columnDefinition = "datetime COMMENT '结束时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
