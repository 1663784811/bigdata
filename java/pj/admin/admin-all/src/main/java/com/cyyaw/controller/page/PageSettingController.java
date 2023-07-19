package com.cyyaw.controller.page;

import java.util.Date;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyyaw.sql.service.CPageComponentsLogService;
import com.cyyaw.sql.service.CPageComponentsService;
import com.cyyaw.sql.service.CPageService;
import com.cyyaw.sql.table.entity.CPage;
import com.cyyaw.sql.table.entity.CPageComponents;
import com.cyyaw.sql.table.entity.CPageComponentsLog;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.WhyStringUtil;
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

    @Autowired
    private CPageComponentsLogService cPageComponentsLogService;

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


    /**
     * 保存组件数据
     *
     * @return
     */
    @PostMapping("/findSetting")
    public BaseResult saveComponents(@RequestBody CPageComponents components) {
        CPageComponents obj = cPageComponentsService.findId(components.getId());
        if (null != obj) {
            // 记录日志
            String data = obj.getData();
            String tid = obj.getTid();
            CPageComponentsLog infoLog = new CPageComponentsLog();
            infoLog.setTid(WhyStringUtil.getUUID());
            infoLog.setCreateTime(new Date());
            infoLog.setNote("");
            infoLog.setPageComponentsId(tid);
            infoLog.setData(data);
            cPageComponentsLogService.save(infoLog);
            // 修改数据
            obj.setData(components.getData());
            cPageComponentsService.save(obj);
        } else {
            return BaseResult.fail("找不到数据:" + JSONUtil.toJsonStr(new JSONObject(components)));
        }
        return BaseResult.ok();
    }


}
