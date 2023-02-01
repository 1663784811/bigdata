package com.cyyaw.tx.admin.controller;

import com.cyyaw.service.TPowerService;
import com.cyyaw.table.tadmin.TPower;
import com.cyyaw.util.tools.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RequestMapping("/admin/tpower")
@RestController
public class TPowerController {

    @Autowired
    private TPowerService tPowerService;


    /**
     * 添加或修改
     */
    @PostMapping("/saveTPower")
    public BaseResult saveTPower(@RequestBody TPower tPower) {
        TPower obj = null;
        Integer id = tPower.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            tPower.setCreateTime(new Date());
            tPower.setTid("");
            log.info("添加:{}", tPower);
            obj = tPowerService.save(tPower);
        } else {
            //修改
            log.info("修改:{}", tPower);
            TPower cpObj = tPowerService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(tPower, cpObj);
            obj = tPowerService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delTPower")
    public BaseResult delTPower(@RequestBody Integer idArr[]) {
        tPowerService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
