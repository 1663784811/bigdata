package com.cyyaw.spider.table.dao;

import com.alibaba.fastjson.annotation.JSONField;
import com.cyyaw.spider.table.entity.PaPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface PaPageDao extends BaseDao<PaPage,String> {


    @Query("select m from PaPage m where m.url = ?1")
    List<PaPage> findByUrl(String url);

}
