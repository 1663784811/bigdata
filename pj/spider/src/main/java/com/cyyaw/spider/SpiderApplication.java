package com.cyyaw.spider;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.spider.data.PageHandleData;
import com.cyyaw.spider.data.PageHandleDataStandard;
import com.cyyaw.spider.data.TagElement;
import com.cyyaw.spider.data.WebPageData;
import com.cyyaw.spider.run.RunSpider;
import com.cyyaw.spider.run.StandardSpider;
import com.cyyaw.spider.table.dao.PaATagDao;
import com.cyyaw.spider.table.dao.PaImgDao;
import com.cyyaw.spider.table.dao.PaPageDao;
import com.cyyaw.spider.table.entity.PaATag;
import com.cyyaw.spider.table.entity.PaImg;
import com.cyyaw.spider.table.entity.PaPage;
import com.cyyaw.spider.util.StringUtilWHY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class SpiderApplication implements ApplicationRunner {


    @Autowired
    private volatile PaPageDao paPageDao;

    @Autowired
    private volatile PaImgDao paImgDao;

    @Autowired
    private volatile PaATagDao paATagDao;


    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class);

    }


    @Override
    public void run(ApplicationArguments args) {
        System.out.println("=================== 启动完成 ==========================");

        RunSpider runSpider = new StandardSpider();


        new Thread(() -> {
            System.out.println("=================== 启动spider ==========================");

            // 线程池 ( 爬取数据 )
            new Thread(() -> {
                boolean iswait = true;
                while (true) {
                    try {
                        if(iswait){
                            if(WebPageData.pageList.size()>100){
                                Thread.sleep(1000L *10);
                            }else{
                                Thread.sleep(100L);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<String> pageUrl = WebPageData.pageUrl;
                    if (pageUrl.size() > 0) {
                        String url = pageUrl.remove(0);
                        // 判断是否被爬取过
                        List<PaPage> pageList = paPageDao.findByUrl(url);
                        if (pageList == null || pageList.size() == 0) {
                            String httpData = runSpider.getHttpData(url);
                            if (null != httpData) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("url", url);
                                map.put("data", httpData);
                                WebPageData.pageList.add(map);
                                System.out.println("================  爬取成功");
                                iswait = true;
                            }else{
                                iswait = false;
                            }
                        }else{
                            iswait = false;
                        }
                    }
                }
            }).start();


            // 线程池 ( 处理数据 )
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        // 获取数据
                        List<Map<String, Object>> pageList = WebPageData.pageList;
                        if (pageList.size() > 0) {
                            System.out.println("=======  处理数据 ");
                            Map<String, Object> page = WebPageData.pageList.remove(0);
                            String data = page.get("data").toString();
                            Object type = page.get("type");
                            Object url = page.get("url");
                            PageHandleData pageHandleData = new PageHandleDataStandard(data);

                            // 获取页面title
                            String pageTitle = pageHandleData.getPageTitle();
                            String description = pageHandleData.getDescription();

                            PaPage paPage = new PaPage();
                            paPage.setId(StringUtilWHY.getUUID());
                            paPage.setNote("");
                            paPage.setCreateTime(new Date());
                            if (null != type) {
                                paPage.setType(type.toString());
                            }
                            paPage.setTitle(pageTitle);
                            paPage.setDescription(description);
                            paPage.setUrl(url.toString());
                            paPage.setContent(data);
                            paPageDao.save(paPage);

                            // 获取页面所有图片数据
                            List<TagElement> imgList = pageHandleData.getTags("img");
                            for (TagElement img : imgList) {
                                PaImg paImg = new PaImg();
                                paImg.setId(StringUtilWHY.getUUID());
                                paImg.setNote("");
                                paImg.setCreateTime(new Date());
                                paImg.setPageId(paPage.getId());
                                paImg.setType(null);
                                JSONObject attributes = img.getAttributes();
                                String alt = attributes.getString("alt");
                                paImg.setAlt(alt);
                                String src = attributes.getString("src");
                                if(null !=src && src.indexOf("data:")!=0){
                                    paImg.setUrl(src);
                                }
                                paImgDao.save(paImg);
                            }

                            // 获取页面所有 A 标签数据
                            List<TagElement> aList = pageHandleData.getTags("a");
                            for (TagElement aTag: aList) {

                                PaATag paATag = new PaATag();
                                paATag.setId(StringUtilWHY.getUUID());
                                paATag.setNote("");
                                paATag.setCreateTime(new Date());
                                paATag.setPageId(paPage.getId());
                                paATag.setTxt(aTag.getText());
                                JSONObject attributes = aTag.getAttributes();
                                String href = attributes.getString("href");
                                if(!ObjectUtils.isEmpty(href)){
                                    paATag.setHref(attributes.getString("href"));
                                    WebPageData.pageUrl.add(href);
                                }
                                paATagDao.save(paATag);

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }).start();

    }
}
