package com.cyyaw.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cyyaw.sql.table.dao.*;
import com.cyyaw.sql.table.entity.CSql;
import com.cyyaw.table.spider.tag.dao.TagDao;
import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.util.SqlCommonUtil;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(tags = "公共模块")
@RestController
@RequestMapping("/admin/{eCode}/common")
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
    public BaseResult<Object> query(@RequestBody @RequestParam Map<String, Object> map, @TokenData LoginInfo loginInfo) {
        JSONObject json = new JSONObject(map);
        return commonDao.query(SqlCommonUtil.setDefaultData(json, loginInfo));
    }


    @ApiOperation(value = "通用修改或添加")
    @RequestMapping("/save")
    public BaseResult<Object> save(@RequestBody Map<String, Object> map) {
        JSONObject json = new JSONObject(map);
        String code = json.getString("code");
        JSONObject data = json.getJSONObject("data");
        return commonDao.save(code, data);
    }


    @ApiOperation(value = "通用删除")
    @RequestMapping("/del")
    public BaseResult<Object> del(@RequestBody @RequestParam Map<String, Object> map, @TokenData LoginInfo loginInfo) {
        JSONObject json = new JSONObject(map);
        return commonDao.del(json);
    }

    @RequestMapping("/saveSql")
    public BaseResult<Object> saveSql(@RequestBody CSql cSql) {
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
}
