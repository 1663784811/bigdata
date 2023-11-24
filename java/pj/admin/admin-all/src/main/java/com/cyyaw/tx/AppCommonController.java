package com.cyyaw.tx;

import com.alibaba.fastjson.JSONObject;
import com.cyyaw.sql.table.dao.*;
import com.cyyaw.table.spider.tag.dao.TagDao;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(tags = "公共模块")
@RestController
@RequestMapping("/app/common")
public class AppCommonController {


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


    /**
     *   {
     *       code: '',
     *       page: 1,
     *       size: 30,
     *       sort: ''
     *   }
     *
     */
    @ApiOperation(value = "通用查询")
    @GetMapping("/query")
    public BaseResult query(@RequestBody @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject(map);
        return commonDao.query(json);
    }


}
