package com.cyyaw.table.spider.entity;


import com.cyyaw.jpa.entity.BaseTable;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "spider_nick_name")
@org.hibernate.annotations.Table(appliesTo = "spider_nick_name", comment = "昵称")
public class SpiderNickName extends BaseTable implements Serializable {

    private static final long serialVersionUID = 157693283L;

    @Basic
    @Column(name = "host", columnDefinition = "varchar(255) COMMENT '来源域名'")
    private String host;

    @Basic
    @Column(name = "url", columnDefinition = "text COMMENT 'url'")
    private String url;

    @Basic
    @Column(name = "nick_name", columnDefinition = "varchar(255) COMMENT '昵称'")
    private String nickName;

    @Basic
    @Column(name = "content", columnDefinition = "text COMMENT '内容'")
    private String content;

    @Basic
    @Column(name = "href", columnDefinition = "text COMMENT 'href'")
    private String href;


}
