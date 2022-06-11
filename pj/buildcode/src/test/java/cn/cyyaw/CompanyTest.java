package cn.cyyaw;

import cn.cyyaw.util.tools.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class CompanyTest {

    @Test
    public void test005()  {
        log.info("===========>>>>>  获取token  <<<<<<<");
        Map params = new JSONObject();
        params.put("appId", "ebab7922b6501175a5038b2af5de5b16");
        params.put("secret","94cfb4364c370c7e5cb82c3368e55293c745d613");
        String url = "https://api.movebroad.cn/park/sec/v1.0.0/login";
        Map<String, String> requestHeaders = new HashMap<>();


        String res = HttpClientUtil.doPost(url, params, null);

        log.info("登录返回数据:: {}", res);
    }


    @Test
    public void test002(){
        Long startTime = 1615724015000L;
        Long endTime= 1618814605606L;

        Long overTime =  (endTime - startTime)/(1000L*60*60*24*365);
        if(overTime>=2){
            System.out.println("==============::"+ overTime);
        }else{
            System.out.println("===========ssss::"+ overTime);
        }


    }

}
