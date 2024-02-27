package com.cyyaw.admin.controller;

import com.cyyaw.service.CpRecruitService;
import com.cyyaw.spider.table.company.entity.CpRecruit;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "公司-招聘职位")
@RestController
@RequestMapping("/admin/{eCode}/company/recruit")
public class CpRecruitController {

    @Autowired
    private CpRecruitService cpRecruitService;


    @PostMapping("/saveCpRecruit")
    public BaseResult saveCpRecruit(@RequestBody CpRecruit saveObj) {
        return BaseResult.ok(cpRecruitService.save(saveObj));
    }


}
