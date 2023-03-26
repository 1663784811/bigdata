package com.cyyaw.tx.web.controller;


import cn.hutool.json.JSONUtil;
import com.cyyaw.tx.web.service.GGoodsService;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/tx/config/page")
@RestController
public class PageSettingController {

    @Autowired
    private GGoodsService gGoodsService;

    @GetMapping("/pageSetting")
    public BaseResult saveGoods(@PathVariable Map<String,Object> map){
        String data = "{    commonTable: {\n" +
                "        requestObj: {\n" +
                "            queryRequest: {\n" +
                "                url: '/admin/store/findPage',\n" +
                "                parameter: {\n" +
                "                    code: 'save_table'\n" +
                "                }\n" +
                "            },\n" +
                "            saveRequest: {\n" +
                "                url: '/admin/common/sql/saveSql',\n" +
                "\n" +
                "            },\n" +
                "            delRequest: {\n" +
                "                url: '/admin/common/sql/delSql',\n" +
                "            }\n" +
                "        },\n" +
                "        search: {\n" +
                "            columns: [\n" +
                "                {\n" +
                "                    key: 'name',\n" +
                "                    name: '名称',\n" +
                "                    type: 'input',\n" +
                "                    note: '名称备注'\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        save: {\n" +
                "\n" +
                "            columns: [\n" +
                "                {\n" +
                "                    key: 'name',\n" +
                "                    name: '名称',\n" +
                "                    type: 'input',\n" +
                "                    note: '名称备注'\n" +
                "                },\n" +
                "                {\n" +
                "                    key: 'name1',\n" +
                "                    name: '名称1',\n" +
                "                    type: 'input',\n" +
                "                    note: '名称备注'\n" +
                "                },\n" +
                "                {\n" +
                "                    key: 'name2',\n" +
                "                    name: '名称2',\n" +
                "                    type: 'input',\n" +
                "                    note: '名称备注'\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        columns: [\n" +
                "            {\n" +
                "                title: 'ID',\n" +
                "                key: 'tid',\n" +
                "                width: 250\n" +
                "            },\n" +
                "            {\n" +
                "                title: '账号',\n" +
                "                key: 'account'\n" +
                "            },\n" +
                "            {\n" +
                "                title: '分类',\n" +
                "                key: 'type'\n" +
                "            },\n" +
                "            {\n" +
                "                title: '备注',\n" +
                "                key: 'tags'\n" +
                "            }\n" +
                "        ],\n" +
                "        operation: {\n" +
                "            show: true,\n" +
                "            update: true,\n" +
                "            del: true\n" +
                "        }\n" +
                "    }}";

       return BaseResult.ok(JSONUtil.parseObj(data));
    }




}
