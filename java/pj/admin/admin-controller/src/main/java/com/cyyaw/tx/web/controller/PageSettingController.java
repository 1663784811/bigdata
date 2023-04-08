package com.cyyaw.tx.web.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cyyaw.service.sql.CPageService;
import com.cyyaw.table.config.entity.CPage;
import com.cyyaw.table.config.entity.CPageComponents;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RequestMapping("/tx/config/page")
@RestController
public class PageSettingController {

    @Autowired
    private CPageService cPageService;


    /**
     * 获取页面配置
     */
    @GetMapping("/pageSetting")
    public BaseResult saveGoods(@PathVariable Map<String, Object> map) {
        JSONObject json = new JSONObject(map);
        String pageId = json.getStr("pageId");
        CPage cPage = cPageService.findByTid(pageId);
        if (null != cPage) {
            JSONObject rest = new JSONObject();
            rest.set("page", cPage);
            JSONArray jsonList = new JSONArray();
            List<CPageComponents> components = cPage.getComponents();
            for (int i = 0; i < components.size(); i++) {
                CPageComponents cPageComponents = components.get(i);
                String data = cPageComponents.getData();
                JSONObject componentsJson = new JSONObject();
                JSONObject jsonData = new JSONObject(data);
                componentsJson.set("componentsData", jsonData);
                componentsJson.set("components", cPageComponents);
                jsonList.add(componentsJson);
            }
            rest.set("componentsList", jsonList);
            return BaseResult.ok(rest);
        } else {
            return BaseResult.fail("找不到页面数据");
        }
    }


}
