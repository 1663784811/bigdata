package com.cyyaw.service.web;

import com.cyyaw.jpa.BaseTableService;
import com.cyyaw.table.web.entity.WebImage;


public interface WebImageService extends BaseTableService<WebImage, Integer> {

    WebImage findByTid(String tid);

}
