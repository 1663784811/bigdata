package com.cyyaw.spider.table.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "pa_a_tag")
@org.hibernate.annotations.Table(appliesTo = "pa_a_tag", comment = "a标签")
public class PaATag implements Serializable {

    private static final long serialVersionUID = 1589138009706L;

    @Id
    @Basic
    @Column(name = "id", columnDefinition = "varchar(32) not null COMMENT 'id'")
    private String id;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
    @Basic
    @Column(name = "create_time", length = 19, columnDefinition = "datetime default now() COMMENT '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Basic
    @Column(name = "page_id",  columnDefinition = "varchar(32) COMMENT '页面ID'")
    private String pageId;

    @Basic
    @Column(name = "txt", columnDefinition = "text COMMENT '文字说明'")
    private String txt;

    @Basic
    @Column(name = "href",  columnDefinition = "text COMMENT 'href'")
    private String href;

    @Basic
    @Column(name = "dn",  columnDefinition = "varchar(255) COMMENT '域名'")
    private String dn;

}
