package com.cyyaw.browser.utils;

import java.util.Date;

public class BrowserUtil {


    public static String getFileName(String url) {
        long time = new Date().getTime();
        String fileName = url.replace("http:", "__HTTP__").replace("https:", "__HTTPS__").replace("/", "__s__");
        return fileName + "__time__" + time;
    }

}
