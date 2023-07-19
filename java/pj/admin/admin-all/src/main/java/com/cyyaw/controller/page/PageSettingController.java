package com.cyyaw.controller.page;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cyyaw.sql.service.CPageComponentsService;
import com.cyyaw.sql.service.CPageService;
import com.cyyaw.sql.table.entity.CPage;
import com.cyyaw.sql.table.entity.CPageComponents;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("/tx/config/page")
@RestController
public class PageSettingController {

    @Autowired
    private CPageService cPageService;

    @Autowired
    private CPageComponentsService cPageComponentsService;


    /**
     * 获取页面配置
     */
    @GetMapping("/pageSetting")
    public BaseResult pageSetting(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject(map);
        String pageCode = json.getStr("pageCode");
        CPage cPage = cPageService.findByPageCode(pageCode);
        if (null != cPage) {
            JSONObject rest = new JSONObject();
            // 查找页面组件
            List<CPageComponents> components = cPage.getComponents();
            for (int i = 0; i < components.size(); i++) {
                CPageComponents cPageComponents = components.get(i);
                String data = cPageComponents.getData();
                if (StrUtil.isBlank(data)) {
                    data = "{}";
                }
                // 通用表格
                rest.set("commonTable", new JSONObject(data));
            }
            return BaseResult.ok(rest);
        } else {
            return BaseResult.fail("找不到页面数据");
        }
    }

    @GetMapping("/findSetting")
    public BaseResult findSetting(CPageComponents obj) {
        JSONObject rest = new JSONObject();
        List<CPageComponents> components = cPageComponentsService.findByExample(obj);
        for (int i = 0; i < components.size(); i++) {
            CPageComponents cPageComponents = components.get(i);
            String data = cPageComponents.getData();
            if (StrUtil.isBlank(data)) {
                data = "{}";
            }
            // 通用表格
            rest.set("commonTable", new JSONObject(data));
        }
        return BaseResult.ok(rest);
    }


}
