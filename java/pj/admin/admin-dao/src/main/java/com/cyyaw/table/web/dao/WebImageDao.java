package com.cyyaw.table.web.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.web.entity.WebImage;
import org.springframework.data.jpa.repository.Query;

public interface WebImageDao extends BaseDao<WebImage, Integer> {


    @Query("select m from WebImage m where m.tid = ?1")
    WebImage findByTid(String tid);

}
