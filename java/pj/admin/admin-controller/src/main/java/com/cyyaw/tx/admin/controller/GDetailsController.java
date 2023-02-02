package com.cyyaw.tx.admin.controller;


import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.service.GDetailsService;
import com.cyyaw.table.store.goods.GDetails;
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
@RequestMapping("/admin/gdetails")
@RestController
public class GDetailsController {

    @Autowired
    private GDetailsService gDetailsService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageGDetails")
    public BaseResult findPageGDetails(String jsonStr, SelectEntity selectEntity) {
        PageRespone<GDetails> page = gDetailsService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdGDetails")
    public BaseResult findIdGDetails(Integer id) {
        GDetails obj = gDetailsService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveGDetails")
    public BaseResult saveGDetails(@RequestBody GDetails gDetails) {
        GDetails obj = null;
        Integer id = gDetails.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            gDetails.setCreatetime(new Date());
            log.info("添加:{}", gDetails);
            obj = gDetailsService.save(gDetails);
        } else {
            //修改
            log.info("修改:{}", gDetails);
            GDetails cpObj = gDetailsService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(gDetails, cpObj);
            obj = gDetailsService.save(gDetails);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delGDetails")
    public BaseResult delGDetails(@RequestBody Integer idArr[]) {
        gDetailsService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
