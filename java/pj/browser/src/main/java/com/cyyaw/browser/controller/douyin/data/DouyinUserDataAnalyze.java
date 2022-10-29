package com.cyyaw.browser.controller.douyin.data;

import cn.hutool.core.util.StrUtil;
import com.cyyaw.browser.controller.data.DataAnalyzeAbstract;
import com.cyyaw.browser.listen.SpiderData;
import com.cyyaw.table.spider.dao.SpiderNickNameDao;
import com.cyyaw.table.spider.entity.SpiderNickName;
import com.cyyaw.util.tools.WhyStringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 抖音用户数据分析
 */
@Component
public class DouyinUserDataAnalyze extends DataAnalyzeAbstract {

    @Autowired
    private SpiderNickNameDao spiderNickNameDao;

    @Override
    public boolean urlRule(String url) {
        return true;
    }

    @Override
    public void analyze(Document document, SpiderData spiderData) {
        String url = spiderData.getUrl();
        String host = spiderData.getHost();
        Elements elements = document.select(".ARHQtNo4");
        for (Element element : elements) {
            String nickName = element.select(".J9zL1Z55").first().text();
            if(StrUtil.isNotBlank(nickName)){
                String content = element.select(".NDykH66P").first().text();
                String href = element.select("a").first().attr("href");
                SpiderNickName spiderNickName = new SpiderNickName();
                spiderNickName.setHost(host);
                spiderNickName.setUrl(url);
                spiderNickName.setNickName(nickName);
                spiderNickName.setContent(content);
                spiderNickName.setHref(href);
                spiderNickName.setTid(WhyStringUtil.getUUID());
                spiderNickName.setCreateTime(new Date());
                spiderNickName.setDel(0);
                spiderNickName.setNote("抖音");
                spiderNickNameDao.save(spiderNickName);
            }
        }
    }


}
