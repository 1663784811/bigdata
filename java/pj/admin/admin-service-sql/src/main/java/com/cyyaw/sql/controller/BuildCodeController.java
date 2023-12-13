package com.cyyaw.sql.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyyaw.sql.buildcode.croe.database.DataBase;
import com.cyyaw.sql.buildcode.croe.entity.java.JavaColumn;
import com.cyyaw.sql.buildcode.croe.entity.java.JavaData;
import com.cyyaw.sql.buildcode.croe.entity.vue.Filters;
import com.cyyaw.sql.buildcode.croe.entity.vue.VueJson;
import com.cyyaw.sql.buildcode.croe.tools.OperationTools;
import com.cyyaw.sql.buildcode.croe.tools.TypeTools;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j

@Api(tags = "构建代码")
@RequestMapping("/admin/buildCode")
@RestController
public class BuildCodeController {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/loadTable")
    @ApiOperation(value = "加载数据表格", notes = "加载数据表格")
    public BaseResult loadTable() throws SQLException, ClassNotFoundException {
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        DataBase dataBase = new DataBase(connection);
        List<JavaData> tableList = dataBase.getTableList(null);

        JSONArray arr = new JSONArray();

        for (int i = 0; i < tableList.size(); i++) {
            JavaData javaData = tableList.get(i);
            JSONObject rest = new JSONObject();
            rest.set("commonTable", commonTableFn(javaData));
            rest.set("newTable", newTableFn(javaData));
            JSONObject jsobj = new JSONObject();
            jsobj.set("pageConfig", rest);
            jsobj.set("table", javaData.getTable());
            jsobj.set("database", javaData.getDatabase());
            jsobj.set("note", javaData.getTableNote());
            arr.add(jsobj);
        }
        return BaseResult.ok(arr);
    }


    private JSONObject commonTableFn(JavaData javaData) {
        JSONObject js = new JSONObject();
        List<JavaColumn> javaColumns = javaData.getJavaColumns();
        String table = javaData.getTable();
        String strurl = OperationTools.indexToLowerCase(table);
        String strTable = OperationTools.indexToUpperCase(table);
        JSONObject requestObj = new JSONObject();
        JSONObject queryRequest = new JSONObject();
        queryRequest.set("url", "/admin/" + strurl + "/findPage");
        queryRequest.set("show", true);
        requestObj.set("queryRequest", queryRequest);
        JSONObject saveRequest = new JSONObject();
        saveRequest.set("url", "/admin/" + strurl + "/save" + strTable);
        saveRequest.set("show", true);
        requestObj.set("saveRequest", saveRequest);
        JSONObject delRequest = new JSONObject();
        delRequest.set("url", "/admin/" + strurl + "/del" + strTable);
        delRequest.set("show", true);
        requestObj.set("delRequest", delRequest);
        JSONObject operation = new JSONObject();
        operation.set("title", "操作");
        operation.set("key", "operation");
        operation.set("width", 200);
        JSONArray operationArr = new JSONArray();
        JSONObject arr1 = new JSONObject();
        arr1.set("label", "查看");
        arr1.set("even", "");
        operationArr.add(arr1);
        JSONObject arr2 = new JSONObject();
        arr2.set("label", "修改");
        arr2.set("even", "");
        operationArr.add(arr2);
        JSONObject arr3 = new JSONObject();
        arr3.set("label", "删除");
        arr3.set("even", "");
        operationArr.add(arr3);
        operation.set("operationArr", operationArr);
        // ===================
        List<VueJson> vueJsons = TypeTools.javaColumnList2VueJsonList(javaColumns);
        for (int j = 0; j < vueJsons.size(); j++) {
            VueJson vueJson = vueJsons.get(j);
            List<Filters> filters = vueJson.getFilters();
            if (null != filters) {
                if (filters.size() == 0) {
                    vueJson.setFilters(null);
                }
            }
            String key = vueJson.getKey();
            String message = vueJson.getMessage();
            if ("id".equals(key)) {
                vueJson.setWidth(60);
                vueJson.setControlType("hidden");
            }
            if ("del".equals(key)) {
                vueJsons.remove(j);
                j--;
                continue;
            }
            if ("tid".equals(key)) {
                vueJson.setTooltip(true);
                vueJson.setIsShowColumn(false);
                vueJson.setControlType("hidden");
            }
            if ("createTime".equals(key)) {
                vueJson.setWidth(160);
                vueJson.setControlType("hidden");
            }
            if (null != message && message.indexOf("名") != -1) {
                vueJson.setIsShowSearch(true);
            }
        }
        js.set("requestObj", requestObj);
        js.set("operation", operation);
        js.set("columns", vueJsons);
        return js;
    }


    private JSONObject newTableFn(JavaData javaData) {


        List<JavaColumn> javaColumns = javaData.getJavaColumns();
        List<VueJson> vueJsons = TypeTools.javaColumnList2VueJsonList(javaColumns);
        for (int j = 0; j < vueJsons.size(); j++) {
            VueJson vueJson = vueJsons.get(j);
            List<Filters> filters = vueJson.getFilters();
            if (null != filters) {
                if (filters.size() == 0) {
                    vueJson.setFilters(null);
                }
            }
            String key = vueJson.getKey();
            String message = vueJson.getMessage();
            if ("id".equals(key)) {
                vueJson.setWidth(60);
                vueJson.setControlType("hidden");
            }
            if ("del".equals(key)) {
                vueJsons.remove(j);
                j--;
                continue;
            }
            if ("tid".equals(key)) {
                vueJson.setTooltip(true);
                vueJson.setIsShowColumn(false);
                vueJson.setControlType("hidden");
            }
            if ("createTime".equals(key)) {
                vueJson.setWidth(160);
                vueJson.setControlType("hidden");
            }
            if (null != message && message.indexOf("名") != -1) {
                vueJson.setIsShowSearch(true);
            }
        }

        // =================    搜索
        JSONObject searchObj = new JSONObject();
        searchObj.set("show", true);
        searchObj.set("columns", searchObjFn(vueJsons));

        // =================  表格
        JSONObject tableObj = new JSONObject();
        JSONObject queryRequest = new JSONObject();
        queryRequest.set("url", "");
        tableObj.set("queryRequest", queryRequest);

        tableObj.set("columns", tableObjFn(vueJsons));
        tableObj.set("loading", true);
        tableObj.set("showColumns", false);
        tableObj.set("operationObj", new JSONObject());

        // =================  保存
        JSONObject saveObj = new JSONObject();
        saveObj.set("show", true);

        JSONObject js = new JSONObject();
        js.set("searchObj", searchObj);
        js.set("tableObj", tableObj);
        js.set("saveObj", saveObj);
        return js;
    }


    private JSONArray tableObjFn(List<VueJson> vueJsons) {
        return JSONUtil.parseArray(JSONUtil.toJsonStr(vueJsons));
    }


    private JSONArray searchObjFn(List<VueJson> vueJsons) {
        String search = "{\n" +
                "        even: 'search',\n" +
                "        name: '搜索',\n" +
                "        show: true,\n" +
                "        icon: 'ios-search',\n" +
                "        type: 'success',\n" +
                "        url: '',\n" +
                "        parameter: []\n" +
                "      }";


        JSONObject searchObj = new JSONObject(search);
        searchObj.set("parameter", tableObjFn(vueJsons));

        String save = "{\n" +
                "        even: 'save',\n" +
                "        name: '添加',\n" +
                "        show: true,\n" +
                "        icon: 'ios-search',\n" +
                "        type: 'warning',\n" +
                "        url: '',\n" +
                "        parameter: []\n" +
                "      }";


        JSONObject saveObj = new JSONObject(save);


        String del = "{\n" +
                "        even: 'del',\n" +
                "        name: '删除',\n" +
                "        show: true,\n" +
                "        icon: 'ios-search',\n" +
                "        type: 'error',\n" +
                "        url: '',\n" +
                "        parameter: []\n" +
                "      }";
        JSONObject delObj = new JSONObject(del);

        JSONArray arr = new JSONArray();
        arr.add(searchObj);
        arr.add(saveObj);
        arr.add(delObj);
        return arr;
    }


}
