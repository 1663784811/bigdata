package com.cyyaw.web.controller;

import cn.hutool.json.JSONObject;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import com.cyyaw.util.tools.WhyStringUtil;
import com.cyyaw.web.service.WBannerService;
import com.cyyaw.web.table.entity.WBanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RequestMapping("/admin/wBanner")
@RestController
public class WBannerController {

    @Autowired
    private WBannerService wBannerService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<WBanner> findPageWBanner(@RequestParam Map<String, Object> map) {
        PageRespone<WBanner> page = wBannerService.findPage(new JSONObject(map));
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
    public BaseResult saveWBanner(@RequestBody WBanner saveObj) {
        WBanner obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = wBannerService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            WBanner cpObj = wBannerService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj, cpObj);
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
