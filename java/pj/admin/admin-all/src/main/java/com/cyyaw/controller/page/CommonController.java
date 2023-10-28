package com.cyyaw.controller.page;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cyyaw.jpa.util.entity.CommonSaveData;
import com.cyyaw.sql.table.dao.*;
import com.cyyaw.sql.table.entity.CSql;
import com.cyyaw.table.spider.tag.dao.TagDao;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.CommonRest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(tags = "公共模块")
@RestController
@RequestMapping("/admin/common")
public class CommonController {


    @Autowired
    private CommonDao commonDao;

    @Autowired
    private CPageDao cPageDao;

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private CFieldDao cFieldDao;

    @Autowired
    private CSqlDao cSqlDao;


    @ApiOperation(value = "通用查询")
    @GetMapping("/query")
    public BaseResult query(@RequestBody @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject(map);
        CommonRest query = commonDao.query(json);
        return BaseResult.ok(query);
    }


    @ApiOperation(value = "通用修改或添加")
    @RequestMapping("/save")
    public BaseResult save(@RequestBody CommonSaveData commonSaveData) {
        Map<String, Object> update = commonDao.save(commonSaveData);
        return BaseResult.ok(update);
    }


    @ApiOperation(value = "通用删除")
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestBody JSONObject json) {
        return commonDao.delete(json);
    }


    @GetMapping("/sqlList")
    public BaseResult sqlList(@RequestBody @RequestParam Map<String, Object> json) {
        String sqlCount = "select count(*) as count from c_sql";
        String sqlContent = "select * from c_sql ";
        boolean touName = true;
        CommonRest query = commonDao.query(sqlCount, sqlContent, new JSONObject(json), touName);
        return BaseResult.ok(query);
    }

    @RequestMapping("/saveSql")
    public BaseResult saveSql(@RequestBody CSql cSql) {
        String tid = cSql.getTid();
        if (StrUtil.isNotBlank(tid)) {
            Integer id = cSql.getId();
            if (null != id) {
                CSql old = cSqlDao.findByid(id);
                BeanUtils.copyProperties(cSql, old);
                CSql data = cSqlDao.save(old);
                return BaseResult.ok(data);
            } else {
                CSql data = cSqlDao.save(cSql);
                return BaseResult.ok(data);
            }
        } else {
            return BaseResult.fail("没有tID");
        }
    }

    /**
     *
     */
    @RequestMapping("/getPageConfig")
    public BaseResult getPageConfig() {
        // 查页面
//        List<CPage> pageList = cPageDao.findAll();
//        JSONObject data = new JSONObject();
//        String aa = "{\"tableCompany\":{\"column\":[{\"title\":\"tid\",\"key\":\"tid\",\"type\":\"selection\"},{\"title\":\"企业法人\",\"key\":\"legal_person\"},{\"title\":\"公司名称\",\"key\":\"name\"},{\"title\":\"股票名称\",\"key\":\"stock_name\"},{\"title\":\"股票类型\",\"key\":\"stock_type\"},{\"title\":\"股票号\",\"key\":\"stock_no\"},{\"title\":\"标签\",\"key\":\"tags\"},{\"title\":\"操作\",\"key\":\"operation\"}]},\"department\":{\"column\":[{\"label\":\"东部地区\",\"key\":1,\"children\":[{\"label\":\"总裁部\",\"key\":11},{\"label\":\"财务部\",\"key\":12},{\"label\":\"技术部\",\"key\":13},{\"label\":\"销售部\",\"key\":14}]},{\"label\":\"西部地区\",\"key\":2,\"children\":[{\"label\":\"总裁部\",\"key\":21},{\"label\":\"财务部\",\"key\":22},{\"label\":\"技术部\",\"key\":23},{\"label\":\"销售部\",\"key\":24}]},{\"label\":\"南部地区\",\"key\":3,\"children\":[{\"label\":\"总裁部\",\"key\":31},{\"label\":\"财务部\",\"key\":32},{\"label\":\"技术部\",\"key\":33},{\"label\":\"销售部\",\"key\":34}]},{\"label\":\"北部地区\",\"key\":4,\"children\":[{\"label\":\"总裁部\",\"key\":41},{\"label\":\"财务部\",\"key\":42},{\"label\":\"技术部\",\"key\":43},{\"label\":\"销售部\",\"key\":44}]}]}}";
//        JSONObject json = JSONObject.parseObject(aa);
//        // 查组件
//        List<CPageComponents> cPageComponentsList = cPageComponentsDao.findAll();
//        // 查表格字段
//        List<Tag> tagList = tagDao.findAll();
//
//        for (int i = 0; i < pageList.size(); i++) {
//            CPage cPage = pageList.get(i);
//            String tid = cPage.getTid();
//            String pageCode = cPage.getPageCode();
//            JSONObject cpData = new JSONObject();
//            for (int j = 0; j < cPageComponentsList.size(); j++) {
//                CPageComponents cPageComponents = cPageComponentsList.get(j);
//                String pageId = cPageComponents.getPageId();
//                if (tid.equals(pageId)) {
//                    String components_code = cPageComponents.getComponents_code();
//                    if (StrUtil.isNotBlank(components_code)) {
//                        JSONObject js = JSONObject.parseObject(JSONObject.toJSONString(cPageComponents));
//                        if ("mainTable".equals(components_code)) {
//                            String cPageComponentsTid = cPageComponents.getTid();
//                            List<CField> cFieldList = cFieldDao.findByCPageComponentsId(cPageComponentsTid);
//                            js.put("data", cFieldList);
//                        }
//                        cpData.put(components_code, js);
//                    }
//                }
//            }
//            JSONObject pageRest = new JSONObject();
//            BeanUtils.copyProperties(cPage, pageRest);
//            pageRest.put("data", cpData);
//            if (StrUtil.isNotBlank(pageCode)) {
//                data.put(pageCode, pageRest);
//            }
//        }
//        return BaseResult.ok(data);
        return null;
    }
}
