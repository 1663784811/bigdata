package com.cyyaw.tx;

import cn.hutool.core.util.StrUtil;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.web.service.WBannerService;
import com.cyyaw.web.table.entity.WBanner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "Banner图")
@RestController
@RequestMapping("/shopping/web/banner")
public class ShoppingBannerController {

    @Autowired
    private WBannerService wBannerService;

    @ApiOperation(value = "查询Banner图", notes = "查询Banner图")
    @GetMapping("/findBanner")
    public BaseResult findBanner(WBanner wBanner) {
        if (StrUtil.isNotBlank(wBanner.getEnterpriseId()) || StrUtil.isNotBlank(wBanner.getTid())) {
            List<WBanner> wBannerList = wBannerService.findByExample(wBanner);
            return BaseResult.ok(wBannerList);
        } else {
            return BaseResult.fail();
        }
    }

}
