package com.cyyaw.data;

import java.util.Date;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.cyyaw.sql.service.CPageComponentsObjService;
import com.cyyaw.sql.service.CPageComponentsService;
import com.cyyaw.sql.table.dao.CPageComponentsDao;
import com.cyyaw.sql.table.dao.CPageComponentsObjDao;
import com.cyyaw.sql.table.entity.CPageComponents;
import com.cyyaw.sql.table.entity.CPageComponentsObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据迁移工具 (  页面配置数据  )
 */

@Component
public class PageSettingData {


    @Autowired
    private CPageComponentsService cPageComponentsService;

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    @Autowired
    private CPageComponentsObjService cPageComponentsObjService;


    public void pageComponentsToComponentsObj() {
        // 第一步: 查找所有组件
        List<CPageComponents> componentsList = cPageComponentsService.findAll(null);
        for (int i = 0; i < componentsList.size(); i++) {
            CPageComponents cPageComponents = componentsList.get(i);
            String data = cPageComponents.getData();
            String name = cPageComponents.getName();
            String tid = cPageComponents.getTid();
            if (StrUtil.isNotBlank(data)) {
                JSONObject json = new JSONObject(data);
                for (String key : json.keySet()) {
                    JSONObject jsonObject = json.getJSONObject(key);
                    CPageComponentsObj obj = new CPageComponentsObj();
                    obj.setPageComponentsId(tid);
                    obj.setName(name);
                    obj.setDataKey(key);
                    obj.setData(jsonObject.toString());
                    cPageComponentsObjService.save(obj);
                }
            }
        }
    }


}
