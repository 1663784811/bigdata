package com.cyyaw.spider.data;

import java.util.List;

public interface PageHandleData {


    String getPageTitle();

    String getDescription();

    List<TagElement> getTags(String tag);

}
