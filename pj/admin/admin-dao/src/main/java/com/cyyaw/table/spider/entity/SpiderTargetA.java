package com.cyyaw.table.spider.entity;


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
@Table(name = "spider_target_a")
@org.hibernate.annotations.Table(appliesTo = "spider_target_a", comment = "标签A数据")
public class SpiderTargetA extends BaseTable implements Serializable {

    private static final long serialVersionUID = 1573693283L;

    @Basic
    @Column(name = "host", columnDefinition = "varchar(255) COMMENT '域名'")
    private String host;

    @Basic
    @Column(name = "url", columnDefinition = "text COMMENT 'url'")
    private String url;

    @Basic
    @Column(name = "content", columnDefinition = "text COMMENT '内容'")
    private String content;

    @Basic
    @Column(name = "href", columnDefinition = "text COMMENT 'href'")
    private String href;

}
