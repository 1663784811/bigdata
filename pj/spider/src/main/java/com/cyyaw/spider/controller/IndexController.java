package com.cyyaw.spider.controller;


import com.cyyaw.spider.data.WebPageData;
import com.cyyaw.spider.table.dao.PaATagDao;
import com.cyyaw.spider.table.entity.PaATag;
import com.cyyaw.spider.task.DomainName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private PaATagDao paATagDao;

    @Autowired
    private DomainName domainName;


    @RequestMapping(value = {"", "index"})
    public String index(){
        return "index";
    }


    @RequestMapping("setUrl")
    public String setUrl(String url){
        if(!ObjectUtils.isEmpty(url)){
            WebPageData.pageUrl.add(url);
        }
        return "ok";
    }


    @RequestMapping("test")
    public String test(){
        PageRequest of = PageRequest.of(0, 50000, Sort.by("createTime").descending());
        Page<PaATag> all = paATagDao.findAll(of);
        List<PaATag> content = all.getContent();
        for (PaATag paATag: content) {
            WebPageData.pageUrl.add(paATag.getHref());
        }
        return "ok";
    }

    @RequestMapping("test001")
    public String test001(){
        domainName.updateDomainName();
        return "ok";
    }


}
