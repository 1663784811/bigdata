package com.cyyaw.browser.controller;

import com.cyyaw.util.tools.WhyStringUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.cyyaw.browser.utils.BrowserUtil;
import com.cyyaw.table.spider.dao.SpiderTargetADao;
import com.cyyaw.table.spider.entity.SpiderTargetA;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class DataAnalyzeAbstract implements DataAnalyze {

    @Autowired
    private SpiderTargetADao spiderTargetADao;

    @Override
    public void afterHandle(String host, String url, Document document) {

        // 处理A标签
        Elements aTags = document.getElementsByTag("a");

        for (Element e : aTags) {
            String href = e.attr("href");
            String text = e.text();
            if(null != spiderTargetADao){
                SpiderTargetA spiderTargetA = new SpiderTargetA();
                spiderTargetA.setHost(host);
                spiderTargetA.setUrl(url);
                spiderTargetA.setContent(text);
                spiderTargetA.setHref(href);
                spiderTargetA.setTid(WhyStringUtil.getUUID());
                spiderTargetA.setCreateTime(new Date());
                spiderTargetA.setDel(0);
                spiderTargetA.setNote("");
                spiderTargetADao.save(spiderTargetA);
            }

        }


        // 保存到本地文件
        String path = "P:/file/" + DateUtil.today();
        FileUtil.mkdir(path);
        String fileName = BrowserUtil.getFileName(url);
        FileWriter writer = new FileWriter(path + "/" + fileName);
        writer.write(document.html());
    }

}
