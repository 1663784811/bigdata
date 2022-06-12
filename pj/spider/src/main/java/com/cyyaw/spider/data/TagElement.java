package com.cyyaw.spider.data;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.jsoup.nodes.Attributes;

import java.io.Serializable;

@Data
public class TagElement implements Serializable {

    private String tagName;

    private String text;

    private JSONObject attributes;

}
