package com.cyyaw.tx.enterprise;


import com.cyyaw.service.enterprise.EEnterpriseService;
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
    public void findAll(Map<String,Object> map){


//        eEnterpriseService.find





    }



}
