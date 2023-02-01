package com.cyyaw.tx.admin.controller;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.service.WBannerService;
import com.cyyaw.table.web.WBanner;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RequestMapping("/admin/wbanner")
@RestController
public class WBannerController {

    @Autowired
    private WBannerService wBannerService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageWBanner")
    public BaseResult findPageWBanner(String jsonStr, SelectEntity selectEntity) {
        PageRespone<WBanner> page = wBannerService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdWBanner")
    public BaseResult findIdWBanner(Integer id) {
        WBanner obj = wBannerService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveWBanner")
    public BaseResult saveWBanner(@RequestBody WBanner wBanner) {
        WBanner obj = null;
        Integer id = wBanner.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            wBanner.setCreatetime(new Date());
            wBanner.setTid(IdWorker.nextId());
            log.info("添加:{}", wBanner);
            obj = wBannerService.save(wBanner);
        } else {
            //修改
            log.info("修改:{}", wBanner);
            WBanner cpObj = wBannerService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(wBanner, cpObj);
            obj = wBannerService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delWBanner")
    public BaseResult delWBanner(@RequestBody Integer idArr[]) {
        wBannerService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
