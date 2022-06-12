package com.cyyaw.test;

import com.cyyaw.spider.run.RunSpider;
import com.cyyaw.spider.run.StandardSpider;
import com.cyyaw.spider.util.StringUtilWHY;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestHttp {

   private RunSpider  runSpider = new StandardSpider();



    @Test
    public void test001(){


        String httpData = runSpider.getHttpData("https://www.hao123.com");


        System.out.println(httpData);

    }

    @Test
    public void test002(){

        String line = "dfdsfdsfdshttps://xxx.yyy.cn";
        String domainName = StringUtilWHY.getDomainName(line);
        System.out.println(domainName);

    }

}
