package com.cyyaw.tx;

import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.web.service.WBannerService;
import com.cyyaw.web.table.entity.WBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/banner")
public class BannerController {

    @Autowired
    private WBannerService wBannerService;

    @GetMapping("/findBanner")
    public BaseResult findBanner(WBanner wBanner){
        List<WBanner> wBannerList = wBannerService.findByExample(wBanner);
        return BaseResult.ok(wBannerList);
    }

}
