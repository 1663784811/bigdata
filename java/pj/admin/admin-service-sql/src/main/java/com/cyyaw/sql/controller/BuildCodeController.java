package com.cyyaw.sql.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cyyaw.sql.buildcode.croe.database.DataBase;
import com.cyyaw.sql.buildcode.croe.entity.java.JavaColumn;
import com.cyyaw.sql.buildcode.croe.entity.java.JavaData;
import com.cyyaw.sql.buildcode.croe.entity.vue.Filters;
import com.cyyaw.sql.buildcode.croe.entity.vue.VueJson;
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

import javax.sql.DataSource;
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
            List<JavaColumn> javaColumns = javaData.getJavaColumns();

            JSONObject js = new JSONObject();
            JSONObject requestObj = new JSONObject();
            JSONObject queryRequest = new JSONObject();
            queryRequest.set("url", "/admin/page/findPage");
            requestObj.set("queryRequest", queryRequest);

            JSONObject saveRequest = new JSONObject();
            saveRequest.set("url", "/admin/page/saveCPage");
            requestObj.set("saveRequest", saveRequest);

            JSONObject delRequest = new JSONObject();
            delRequest.set("url", "/admin/page/delCPage");
            requestObj.set("delRequest", delRequest);

            js.set("requestObj", requestObj);
            JSONObject operation = new JSONObject();
            operation.set("show", true);
            operation.set("update", true);
            operation.set("del", true);
            js.set("operation", operation);
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
                if("id".equals(key)){
                    vueJson.setWidth(60);
                }
            }
            js.set("columns", vueJsons);
            JSONObject rest = new JSONObject();
            rest.set("commonTable", js);

            JSONObject jsobj = new JSONObject();
            jsobj.set("pageConfig", rest);
            jsobj.set("table", javaData.getTable());
            jsobj.set("database", javaData.getDatabase());
            jsobj.set("note", javaData.getTableNote());
            arr.add(jsobj);
        }
        return BaseResult.ok(arr);
    }


}
