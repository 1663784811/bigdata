package com.cyyaw.tx.enterprise;


import cn.hutool.json.JSONObject;
import com.cyyaw.service.enterprise.EEnterpriseService;
import com.cyyaw.table.enterprise.entity.EEnterprise;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "企业管理")
@RequestMapping("/admin/enterprise")
@RestController
public class EnterpriseController {

    @Autowired
    private EEnterpriseService eEnterpriseService;

    @GetMapping("/findAll")
    public BaseResult<EEnterprise> findAll(Map<String, Object> map) {
        PageRespone<EEnterprise> page = eEnterpriseService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }


}
