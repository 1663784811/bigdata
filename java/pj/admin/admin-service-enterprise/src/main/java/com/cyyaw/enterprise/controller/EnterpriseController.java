package com.cyyaw.enterprise.controller;


import cn.hutool.json.JSONObject;
import com.cyyaw.enterprise.service.EEnterpriseService;
import com.cyyaw.enterprise.table.dao.EEnterpriseDao;
import com.cyyaw.enterprise.table.entity.EEnterprise;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Api(tags = "企业管理")
@RequestMapping("/admin/enterprise")
@RestController
public class EnterpriseController {

    @Autowired
    private EEnterpriseService eEnterpriseService;

    @Autowired
    private EEnterpriseDao eEnterpriseDao;


    @GetMapping("/findPage")
    public BaseResult<EEnterprise> findPage(@RequestParam Map<String, Object> map) {
        PageRespone<EEnterprise> page = eEnterpriseService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }


}
