package com.cyyaw.admin.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.admin.service.CPageComponentsService;
import com.cyyaw.admin.service.CPageService;
import com.cyyaw.admin.service.CommonService;
import com.cyyaw.table.sql.entity.CPage;
import com.cyyaw.table.sql.entity.CPageComponents;
import com.cyyaw.table.sql.entity.CSql;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 公共模块
 */
@RequestMapping("/home/common")
@RestController
public class CommonController {


    @Autowired
    private CommonService commonService;

    @Autowired
    private CPageService cPageService;

    private CPageComponentsService cPageComponentsService;

    /**
     * 通用查询
     *
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }
        return commonService.query(json);
    }

    /**
     * 通用修改或添加
     */
    @RequestMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }
        return commonService.update(json);
    }

    /**
     * 通用删除
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String key : map.keySet()) {
            json.put(key, map.get(key));
        }
        return commonService.delete(json);
    }


    @RequestMapping("/sqlList")
    public BaseResult sqlList() {
        List<CSql> data = commonService.sqlList();
        return BaseResult.ok(data);
    }

    @RequestMapping("/saveSql")
    public BaseResult saveSql(@RequestBody CSql cSql) {
        CSql data = commonService.saveSql(cSql);
        return BaseResult.ok(data);
    }

    /**
     *
     */
    @RequestMapping("/getPageConfig")
    public BaseResult getPageConfig() {
        List<CPage> pages = cPageService.findAll();


        JSONObject data = new JSONObject();
        String aa = "{\"tableCompany\":{\"column\":[{\"title\":\"tid\",\"key\":\"tid\",\"type\":\"selection\"},{\"title\":\"企业法人\",\"key\":\"legal_person\"},{\"title\":\"公司名称\",\"key\":\"name\"},{\"title\":\"股票名称\",\"key\":\"stock_name\"},{\"title\":\"股票类型\",\"key\":\"stock_type\"},{\"title\":\"股票号\",\"key\":\"stock_no\"},{\"title\":\"标签\",\"key\":\"tags\"},{\"title\":\"操作\",\"key\":\"operation\"}]},\"department\":{\"column\":[{\"label\":\"东部地区\",\"key\":1,\"children\":[{\"label\":\"总裁部\",\"key\":11},{\"label\":\"财务部\",\"key\":12},{\"label\":\"技术部\",\"key\":13},{\"label\":\"销售部\",\"key\":14}]},{\"label\":\"西部地区\",\"key\":2,\"children\":[{\"label\":\"总裁部\",\"key\":21},{\"label\":\"财务部\",\"key\":22},{\"label\":\"技术部\",\"key\":23},{\"label\":\"销售部\",\"key\":24}]},{\"label\":\"南部地区\",\"key\":3,\"children\":[{\"label\":\"总裁部\",\"key\":31},{\"label\":\"财务部\",\"key\":32},{\"label\":\"技术部\",\"key\":33},{\"label\":\"销售部\",\"key\":34}]},{\"label\":\"北部地区\",\"key\":4,\"children\":[{\"label\":\"总裁部\",\"key\":41},{\"label\":\"财务部\",\"key\":42},{\"label\":\"技术部\",\"key\":43},{\"label\":\"销售部\",\"key\":44}]}]}}";
        new JSONObject();
        JSONObject json = JSONObject.parseObject(aa);
        data.put("company", json);
        data.put("people", json);
        data.put("SpiderNickName", json);

        List<CPageComponents> cPageComponentsList = cPageComponentsService.findAll();

        for (int i = 0; i < pages.size(); i++) {
            CPage cPage = pages.get(i);
            String tid = cPage.getTid();
            String pageCode = cPage.getPageCode();
            JSONObject cpData = new JSONObject();
            for (int j = 0; j < cPageComponentsList.size(); j++) {
                CPageComponents cPageComponents = cPageComponentsList.get(i);
                String pageId = cPageComponents.getPageId();
                if (tid.equals(pageId)) {
                    String components_code = cPageComponents.getComponents_code();
                    cpData.put(components_code, cPageComponents);
                }
            }
            PageRest pageRest = new PageRest();
            BeanUtils.copyProperties(cPage, pageRest);
            pageRest.setData(cpData);
            data.put(pageCode, pageRest);
        }


        return BaseResult.ok(data);
    }
}
