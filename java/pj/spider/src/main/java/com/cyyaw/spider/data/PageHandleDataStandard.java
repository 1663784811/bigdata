package com.cyyaw.spider.data;
import com.alibaba.fastjson.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PageHandleDataStandard implements PageHandleData{

    private Document webPage;

    public PageHandleDataStandard(String page){
       this.webPage = Jsoup.parse(page);
    }


    @Override
    public String getPageTitle() {
        return webPage.title();
    }

    @Override
    public String getDescription() {
        Elements metas = webPage.getElementsByTag("meta");
        for (Element meta: metas) {
            String name = meta.attr("name");
            if(null != name && "description".equals(name.toLowerCase())){
                return meta.attr("content");
            }
        }
        return null;
    }

    @Override
    public List<TagElement> getTags(String tag) {
        List<TagElement> obj = new ArrayList<>();
        Elements elements = webPage.getElementsByTag(tag);

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            String tagName = element.tagName();
            String text = element.text();
            Attributes attributes = element.attributes();
            TagElement tagElement = new TagElement();
            tagElement.setTagName(tagName);
            tagElement.setText(text);
            tagElement.setAttributes(new JSONObject());
            if(null != attributes && attributes.size()>0){
                JSONObject json = new JSONObject();
                for (Attribute attribute :attributes) {
                    String key = attribute.getKey();
                    String value = attribute.getValue();
                    json.put(key,value);
                }
                tagElement.setAttributes(json);
            }
            obj.add(tagElement);
        }
        return obj;
    }

}
