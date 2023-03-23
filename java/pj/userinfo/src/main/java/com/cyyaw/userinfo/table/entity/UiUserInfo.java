package com.cyyaw.userinfo.table.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ui_user_info")
@org.hibernate.annotations.Table(appliesTo = "ui_user_info", comment = "用户信息")
public class UiUserInfo extends BaseTable {
    private static final long serialVersionUID = 1573661935283L;

    @Basic
    @Column(name = "[name]", columnDefinition = "varchar(255) COMMENT '用户名称'")
    private String name;
    @Basic
    @Column(name = "[face]", columnDefinition = "varchar(255) COMMENT '头像'")
    private String face;
    @Basic
    @Column(name = "[tag]", nullable = true, columnDefinition = "text COMMENT '标签'")
    private String tag;



}
