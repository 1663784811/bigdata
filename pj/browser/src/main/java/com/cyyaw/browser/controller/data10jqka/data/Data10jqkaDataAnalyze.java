package com.cyyaw.browser.controller.data10jqka.data;


import cn.cyyaw.util.tools.WhyStringUtil;
import cn.hutool.core.net.url.UrlBuilder;
import com.cyyaw.browser.controller.DataAnalyze;
import com.cyyaw.table.company.dao.CpCompanyDao;
import com.cyyaw.table.company.entity.CpCompany;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Data10jqkaDataAnalyze implements DataAnalyze {

    @Autowired
    private CpCompanyDao cpCompanyDao;

    @Override
    public boolean urlRule(String url) {
        UrlBuilder builder = UrlBuilder.ofHttp(url);
        String host = builder.getHost();
        if (host.equals("data.10jqka.com.cn")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void analyze(Document doc) {
        Elements rows = doc.select(".m-table tbody tr");



        for (Element element : rows) {
            Elements td = element.select("td");
            String name = td.get(2).text();
            String no = td.get(1).text();
            CpCompany company = new CpCompany();
            company.setName(name);
            Example<CpCompany> example = Example.of(company);
            List<CpCompany> all = cpCompanyDao.findAll(example);
            if (all.size() == 0) {
                CpCompany cpCompany = new CpCompany();
                cpCompany.setName(name);
                cpCompany.setStockType(1);
                cpCompany.setStockName(name);
                cpCompany.setStockNo(no);
                cpCompany.setEstablishTime(null);
                cpCompany.setLegalPerson(null);
                cpCompany.setTid(WhyStringUtil.getUUID());
                cpCompany.setCreateTime(new Date());
                cpCompany.setDel(0);
                cpCompany.setNote("");
                cpCompanyDao.save(cpCompany);
            }
        }
    }


}
